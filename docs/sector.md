## Request

=== "Java"
        :::java
        AlphaVantage.api()
            .sector()
            .onSuccess((e) -> onData(e))
            .fetch();

**Response Type:**
`SectorResponse`

## Response

=== "Java"
        :::java
        public void onData(SectorResponse reponse){
            SectorUnit realtime = response.getRealTimePerformance();
            String energy = realtime.getEnergy();
            String financials = realtime.getFinancials();
            String industrials  = realtime.getIndustrials();
            String realEstate = realtime.getRealEstate();
            String informationTechnology = realtime.getInformationTechnology();
            String materials = realtime.getMaterials();
            String consumerDiscretionary = realtime.getConsumerDiscretionary();
            String communicationServices = realtime.getCommunicationServices();
            String healthCare = realtime.getHealthCare();
            String consumerStaples = realtime.getConsumerStaples();
            String utilities = realtime.getUtilities();
            ...
        }
