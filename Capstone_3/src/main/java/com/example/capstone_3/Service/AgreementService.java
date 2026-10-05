package com.example.capstone_3.Service;

import com.example.capstone_3.Api.ApiException;
import com.example.capstone_3.DtoIn.AgreementDtoIn;
import com.example.capstone_3.Model.Agreement;
import com.example.capstone_3.Model.Exchange;
import com.example.capstone_3.Repository.AgreementRepository;
import com.example.capstone_3.Repository.ExchangeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        oldAgreement.setProviderAccepted(agreementDtoIn.getProviderAccepted() != null ? agreementDtoIn.getProviderAccepted() : oldAgreement.getProviderAccepted());
        oldAgreement.setReceiverAccepted(agreementDtoIn.getReceiverAccepted() != null ? agreementDtoIn.getReceiverAccepted() : oldAgreement.getReceiverAccepted());
        oldAgreement.setExchange(exchange);

        agreementRepository.save(oldAgreement);
    }

    public void providerAccept(Integer exchangeId) {
        Agreement agreement = findAgreementByExchangeId(exchangeId);
        agreement.setProviderAccepted(true);
        agreementRepository.save(agreement);
    }

    public void receiverAccept(Integer exchangeId) {
        Agreement agreement = findAgreementByExchangeId(exchangeId);
        agreement.setReceiverAccepted(true);
        agreementRepository.save(agreement);
    }

    public Map<String, Object> getAcceptanceStatus(Integer exchangeId) {
        Agreement agreement = findAgreementByExchangeId(exchangeId);

        Map<String, Object> status = new HashMap<>();
        status.put("exchangeId", exchangeId);
        status.put("providerAccepted", agreement.getProviderAccepted());
        status.put("receiverAccepted", agreement.getReceiverAccepted());
        status.put("fullyAccepted", Boolean.TRUE.equals(agreement.getProviderAccepted()) && Boolean.TRUE.equals(agreement.getReceiverAccepted()));

        return status;
    }

    private Agreement findAgreementByExchangeId(Integer exchangeId) {
        Exchange exchange = exchangeRepository.findExchangeById(exchangeId);

        if (exchange == null) {
            throw new ApiException("No exchange found");
        }

        Agreement agreement = agreementRepository.findAgreementByExchange_Id(exchangeId);

        if (agreement == null) {
            throw new ApiException("No agreement found for this exchange");
        }

        return agreement;
    }

    public void delete(Integer id) {
        Agreement oldAgreement = agreementRepository.findAgreementById(id);

        if (oldAgreement == null) {
            throw new ApiException("No agreement found");
        }

        agreementRepository.delete(oldAgreement);
    }
}

