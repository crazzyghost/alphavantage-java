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

    /**
     * Creates the performance breakdown for a single time window, one
     * percentage-change string per sector, exactly as the API returns them.
     *
     * @param informationTechnology the change for the Information Technology sector
     * @param consumerDiscretionary the change for the Consumer Discretionary sector
     * @param healthCare the change for the Health Care sector
     * @param communicationServices the change for the Communication Services sector
     * @param realEstate the change for the Real Estate sector
     * @param utilities the change for the Utilities sector
     * @param financials the change for the Financials sector
     * @param materials the change for the Materials sector
     * @param industrials the change for the Industrials sector
     * @param consumerStaples the change for the Consumer Staples sector
     * @param energy the change for the Energy sector
     */
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

    /**
     * Returns the change for the Information Technology sector.
     *
     * @return the change for the Information Technology sector
     */
    public String getInformationTechnology() {
        return informationTechnology;
    }

    /**
     * Returns the change for the Consumer Discretionary sector.
     *
     * @return the change for the Consumer Discretionary sector
     */
    public String getConsumerDiscretionary() {
        return consumerDiscretionary;
    }

    /**
     * Returns the change for the Health Care sector.
     *
     * @return the change for the Health Care sector
     */
    public String getHealthCare() {
        return healthCare;
    }

    /**
     * Returns the change for the Communication Services sector.
     *
     * @return the change for the Communication Services sector
     */
    public String getCommunicationServices() {
        return communicationServices;
    }

    /**
     * Returns the change for the Real Estate sector.
     *
     * @return the change for the Real Estate sector
     */
    public String getRealEstate() {
        return realEstate;
    }

    /**
     * Returns the change for the Utilities sector.
     *
     * @return the change for the Utilities sector
     */
    public String getUtilities() {
        return utilities;
    }

    /**
     * Returns the change for the Financials sector.
     *
     * @return the change for the Financials sector
     */
    public String getFinancials() {
        return financials;
    }

    /**
     * Returns the change for the Materials sector.
     *
     * @return the change for the Materials sector
     */
    public String getMaterials() {
        return materials;
    }

    /**
     * Returns the change for the Industrials sector.
     *
     * @return the change for the Industrials sector
     */
    public String getIndustrials() {
        return industrials;
    }

    /**
     * Returns the change for the Consumer Staples sector.
     *
     * @return the change for the Consumer Staples sector
     */
    public String getConsumerStaples() {
        return consumerStaples;
    }

    /**
     * Returns the change for the Energy sector.
     *
     * @return the change for the Energy sector
     */
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