package com.wallet.wallet.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.wallet.entity.Wallet;
import com.wallet.wallet.entity.WalletItem;
import com.wallet.wallet.response.Response;
import com.wallet.wallet.dto.WalletItemDTO;
import com.wallet.wallet.service.WalletItemService;
import com.wallet.wallet.util.enums.TypeEnum;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/wallet-item")
@Component
public class WalletItemController {
    @Autowired
    private WalletItemService service;

    @Value("${pagination.items_per_page}")
    private int itemsPerPage;

    @PostMapping
    public ResponseEntity<Response<WalletItemDTO>> create(@Valid @RequestBody WalletItemDTO dto, BindingResult result){

        Response<WalletItemDTO> response = new Response<WalletItemDTO>();

        if (result.hasErrors()){
            result.getAllErrors().forEach(e -> response.getErros().add(e.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        WalletItem wallet;
        try {
            wallet = service.save(this.converDtoToEntity(dto));
            response.setData(this.converEntityToDto(wallet));

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (ParseException e) {
            response.getErros().add("Erro ao formatar data");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        
        
    }

    @GetMapping(value = "/{wallet}")
    public ResponseEntity<Response<Page<WalletItemDTO>>> findBetweenDates(@PathVariable("wallet") Long wallet, @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate){

        Response<Page<WalletItemDTO>> response = new Response<Page<WalletItemDTO>>();

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        
        Date start;
        Date end;

        try{
            start = format.parse(startDate);
            end = format.parse(endDate);
        } catch(ParseException e){
            response.getErros().add("Data mau formatada");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }


        Page<WalletItem> page = service.findBetweenDates(wallet, start, end, 0);

        List<WalletItemDTO> dto = new ArrayList<>();
        page.getContent().forEach(i->dto.add(this.converEntityToDto(i)));
        Page<WalletItemDTO> pageDto = new PageImpl(dto);

        response.setData(pageDto);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(value = "/type/{wallet}")
    public ResponseEntity<Response<List<WalletItemDTO>>> findByWalletAndType(@PathVariable("wallet") Long wallet, @RequestParam("type") String type){

        Response<List<WalletItemDTO>> response = new Response<List<WalletItemDTO>>();
        List<WalletItem> list = service.findByWalletAndType(wallet, TypeEnum.getEnum(type));

        List<WalletItemDTO> dto = new ArrayList<>();
        list.forEach(i->dto.add(this.converEntityToDto(i)));

        response.setData(dto);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(value = "/total/{wallet}")
    public ResponseEntity<Response<BigDecimal>> sumByWalletId(@PathVariable("wallet") Long wallet){

        Response<BigDecimal> response = new Response<BigDecimal>();
        BigDecimal value = service.sumByWalletId(wallet);
        response.setData(value == null ? BigDecimal.ZERO : value);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping
    public ResponseEntity<Response<WalletItemDTO>> update(@Valid @RequestBody WalletItemDTO dto, BindingResult result){

        Response<WalletItemDTO> response = new Response<WalletItemDTO>();

        Optional<WalletItem> wi = service.findById(dto.getId());

        if(!wi.isPresent()){
            result.addError(new ObjectError("WalletItem", "WalletItem nao encontrado"));
        } else if(wi.get().getWallet().getId().compareTo(dto.getWallet()) != 0){
            result.addError(new ObjectError("WalletItemChanged", "Voce nao pode alterar a carteira"));
        }

        if (result.hasErrors()){
            result.getAllErrors().forEach(e -> response.getErros().add(e.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        
        WalletItem saved;
        try {
            saved = service.save(this.converDtoToEntity(dto));
            response.setData(this.converEntityToDto(saved));

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (ParseException e) {
            response.getErros().add("Erro ao formatar data");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        
    }
    
    @DeleteMapping(value="/{walletItemId}")
    public ResponseEntity<Response<String>> delete(@PathVariable("walletItemId") Long wallet){

        Response<String> response = new Response<String>();

        Optional<WalletItem> wi = service.findById(wallet);

        if(!wi.isPresent()){
            response.getErros().add("Item de id " + wallet + " nao encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        service.deleteById(wallet);
        response.setData("Item de id " + wallet + " apagado com sucesso");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    private WalletItem converDtoToEntity(WalletItemDTO dto) throws ParseException{
        WalletItem u = new WalletItem();
        u.setId(dto.getId());

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

        u.setDate(format.parse(dto.getDate()));
        u.setDescription(dto.getDescription());
        u.setValue(dto.getValue());
        u.setType(TypeEnum.getEnum(dto.getType()));

        Wallet w = new Wallet();
        w.setId(dto.getWallet());
        u.setWallet(w);

        return u;
    }

    private WalletItemDTO converEntityToDto(WalletItem dto){
        WalletItemDTO u = new WalletItemDTO();
        u.setId(dto.getId());

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

        u.setDate(format.format(dto.getDate()));
        u.setDescription(dto.getDescription());
        u.setValue(dto.getValue());
        u.setType(dto.getType().getValue());
        u.setWallet(dto.getWallet().getId());

        return u;
    }
    
}
