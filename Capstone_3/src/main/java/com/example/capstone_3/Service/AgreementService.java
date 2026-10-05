package com.example.capstone_3.Service;

import com.example.capstone_3.Api.ApiException;
import com.example.capstone_3.DtoIn.AgreementDtoIn;
import com.example.capstone_3.Model.Agreement;
import com.example.capstone_3.Model.Exchange;
import com.example.capstone_3.Repository.AgreementRepository;
import com.example.capstone_3.Repository.ExchangeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgreementService {

    private final AgreementRepository agreementRepository;
    private final ExchangeRepository exchangeRepository;

    public List<Agreement> get() {
        return agreementRepository.findAll();
    }

    public void add(AgreementDtoIn agreementDtoIn) {
        Exchange exchange = exchangeRepository.findExchangeById(agreementDtoIn.getExchangeId());

        if (exchange == null) {
            throw new ApiException("No exchange found");
        }

        if (agreementRepository.findAgreementByExchange_Id(exchange.getId()) != null) {
            throw new ApiException("An agreement already exists for this exchange");
        }

        Agreement agreement = new Agreement();
        agreement.setContent(agreementDtoIn.getContent());

        agreement.setProviderAccepted(agreementDtoIn.getProviderAccepted() != null ? agreementDtoIn.getProviderAccepted() : false);
        agreement.setReceiverAccepted(agreementDtoIn.getReceiverAccepted() != null ? agreementDtoIn.getReceiverAccepted() : false);

        agreement.setExchange(exchange);
        agreementRepository.save(agreement);
    }

    public void update(Integer id, AgreementDtoIn agreementDtoIn) {
        Agreement oldAgreement = agreementRepository.findAgreementById(id);

        if (oldAgreement == null) {
            throw new ApiException("No agreement found");
        }

        Exchange exchange = exchangeRepository.findExchangeById(agreementDtoIn.getExchangeId());

        if (exchange == null) {
            throw new ApiException("No exchange found");
        }

        if (!oldAgreement.getExchange().getId().equals(exchange.getId()) && agreementRepository.findAgreementByExchange_Id(exchange.getId()) != null) {
            throw new ApiException("An agreement already exists for this exchange");
        }

        oldAgreement.setContent(agreementDtoIn.getContent());
        oldAgreement.setProviderAccepted(agreementDtoIn.getProviderAccepted() != null ? agreementDtoIn.getProviderAccepted() : false);
        oldAgreement.setReceiverAccepted(agreementDtoIn.getReceiverAccepted() != null ? agreementDtoIn.getReceiverAccepted() : false);
        oldAgreement.setExchange(exchange);
        agreementRepository.save(oldAgreement);
    }

    public void delete(Integer id) {
        Agreement oldAgreement = agreementRepository.findAgreementById(id);

        if (oldAgreement == null) {
            throw new ApiException("No agreement found");
        }
        agreementRepository.delete(oldAgreement);
    }
}
