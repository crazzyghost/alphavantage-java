## Request

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .sector()
        .onSuccess(e -> onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .sector()
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`SectorResponse`

## Response

=== ":material-language-java: Java"
    ```java
    public void onData(SectorResponse response){
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
    }
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    fun onData(response: SectorResponse) {
        val realtime = response.realTimePerformance
        val energy = realtime.energy
        val financials = realtime.financials
        val industrials = realtime.industrials
        val realEstate = realtime.realEstate
        val informationTechnology = realtime.informationTechnology
        val materials = realtime.materials
        val consumerDiscretionary = realtime.consumerDiscretionary
        val communicationServices = realtime.communicationServices
        val healthCare = realtime.healthCare
        val consumerStaples = realtime.consumerStaples
        val utilities = realtime.utilities
    }
    ```
