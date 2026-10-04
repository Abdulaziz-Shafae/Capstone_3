package com.example.capstone_3.Service;

import com.example.capstone_3.Api.ApiException;
import com.example.capstone_3.DtoIn.ExchangeDtoIn;
import com.example.capstone_3.Model.Exchange;
import com.example.capstone_3.Repository.ExchangeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExchangeService {

    private final ExchangeRepository exchangeRepository;


    public List<Exchange> get(){
        return exchangeRepository.findAll();
    }


    public void add(ExchangeDtoIn exchangeDtoIn){

        Exchange exchange = new Exchange();

        exchange.setTokenAmount(exchangeDtoIn.getTokenAmount());

        exchange.setStatus("PENDING");
        exchange.setCreatedAt(LocalDateTime.now());
        exchange.setCompletedAt(null);

        exchangeRepository.save(exchange);
    }


    public void update(Integer id, ExchangeDtoIn exchangeDtoIn){

        Exchange oldExchange = exchangeRepository.findExchangeById(id);

        if(oldExchange == null){
            throw new ApiException("No exchange found");
        }

        oldExchange.setTokenAmount(exchangeDtoIn.getTokenAmount());

        exchangeRepository.save(oldExchange);
    }


    public void delete(Integer id){

        Exchange oldExchange = exchangeRepository.findExchangeById(id);

        if(oldExchange == null){
            throw new ApiException("No exchange found");
        }

        exchangeRepository.delete(oldExchange);
    }

}