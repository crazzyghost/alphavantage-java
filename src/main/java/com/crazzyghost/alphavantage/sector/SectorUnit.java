package com.crazzyghost.alphavantage.sector;

public class SectorUnit {
    private double informationTechnology;
    private double consumerDiscretionary;
    private double healthCare;
    private double communicationServices;
    private double realEstate;
    private double utilities;
    private double financials;
    private double materials;
    private double industrials;
    private double consumerStaples;
    private double energy;

    public SectorUnit(
        double informationTechnology, 
        double consumerDiscretionary, 
        double healthCare,
        double communicationServices, 
        double realEstate, 
        double utilities, 
        double financials, 
        double materials,
        double industrials, 
        double consumerStaples,
        double energy
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

    public double getInformationTechnology() {
        return informationTechnology;
    }
    
    public double getConsumerDiscretionary() {
        return consumerDiscretionary;
    }

    public double getHealthCare() {
        return healthCare;
    }

    public double getCommunicationServices() {
        return communicationServices;
    }

    public double getRealEstate() {
        return realEstate;
    }

    public double getUtilities() {
        return utilities;
    }

    public double getFinancials() {
        return financials;
    }

    public double getMaterials() {
        return materials;
    }

    public double getIndustrials() {
        return industrials;
    }

    public double getConsumerStaples() {
        return consumerStaples;
    }

    public double getEnergy() {
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