/*
 *
 * Copyright (c) 2020 Sylvester Sefa-Yeboah
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.crazzyghost.alphavantage.sector.response;

/**
 * The performance of each of the eleven market sectors over a single time window,
 * as percentage-change strings exactly as the API returns them.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.4.0
 */
public final class SectorUnit {

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