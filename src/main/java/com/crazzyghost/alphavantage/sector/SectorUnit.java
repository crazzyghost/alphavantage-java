package com.crazzyghost.alphavantage.sector;

/**
 * @author crazzyghost
 * @since 1.4.0
 * A sector performance data structure
 */
public class SectorUnit {

    private String informationTechnology;
    private String consumerDiscretionary;
    private String healthCare;
    private String communicationServices;
    private String realEstate;
    private String utilities;
    private String financials;
    private String materials;
    private String industrials;
    private String consumerStaples;
    private String energy;

    public SectorUnit(
        String informationTechnology, 
        String consumerDiscretionary, 
        String healthCare,
        String communicationServices, 
        String realEstate, 
        String utilities, 
        String financials, 
        String materials,
        String industrials, 
        String consumerStaples,
        String energy
    ) {
        this.informationTechnology = informationTechnology;
        this.consumerDiscretionary = consumerDiscretionary;
        this.healthCare = healthCare;
        this.communicationServices = communicationServices;
        this.realEstate = realEstate;
        this.utilities = utilities;
        this.financials = financials;
        this.materials = materials;
        this.industrials = industrials;
        this.consumerStaples = consumerStaples;
        this.energy = energy;
    }

    public String getInformationTechnology() {
        return informationTechnology;
    }
    
    public String getConsumerDiscretionary() {
        return consumerDiscretionary;
    }

    public String getHealthCare() {
        return healthCare;
    }

    public String getCommunicationServices() {
        return communicationServices;
    }

    public String getRealEstate() {
        return realEstate;
    }

    public String getUtilities() {
        return utilities;
    }

    public String getFinancials() {
        return financials;
    }

    public String getMaterials() {
        return materials;
    }

    public String getIndustrials() {
        return industrials;
    }

    public String getConsumerStaples() {
        return consumerStaples;
    }

    public String getEnergy() {
        return energy;
    }

    @Override
    public String toString() {
        return "SectorUnit { communicationServices=" + communicationServices + 
            ", consumerDiscretionary=" + consumerDiscretionary + 
            ", consumerStaples=" + consumerStaples + 
            ", energy=" + energy + 
            ", financials=" + financials + 
            ", healthCare=" + healthCare + 
            ", industrials=" + industrials + 
            ", informationTechnology=" + informationTechnology + 
            ", materials=" + materials + 
            ", realEstate=" + realEstate + 
            ", utilities=" + utilities + 
        "}";
    }

    
}