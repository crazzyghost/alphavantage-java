[alphavantage](../alphavantage/index.md) / [sector](./index.md) / [response](./response.md) / [SectorResponse](./sectorresponse.md)

# SectorResponse

`public final class SectorResponse extends Object`

A Sector response

### Types

|Name|Summary|
|----|-------|
| [MetaData](#) | `public static class MetaData` |
| [SectorParser](#) | `public static class SectorParser` |

### Constructors

|Name|Summary|
|----|-------|
| [SectorResponse](./sectorresponse.md) | `private SectorResponse(String errorMessage)` |
| [SectorResponse](./sectorresponse.md) | `private SectorResponse(MetaData metaData, Map<String, SectorUnit> sectorUnits)` |

### Properties

|Name|Summary|
|----|-------|
| [metaData](#) | `private MetaData metaData` |
| [sectorUnits](#) | `private Map<String, SectorUnits> sectorUnits` |
| [errorMessage](#) | `private String errorMessage` |

### Methods

|Name|Summary|
|----|-------|
| [getMetaData](#) | `public MetaData getMetaData()` |
| [getErrorMessage](#) | `public String getErrorMessage()` |
| [getRealTimePerformance](#) | `public SectorUnit getRealTimePerformance()` |
| [getOneDayPerformance](#) | `public SectorUnit getOneDayPerformance()` |
| [getFiveDayPerformance](#) | `public SectorUnit getFiveDayPerformance()` |
| [getOneMonthPerformance](#) | `public SectorUnit getOneMonthPerformance()` |
| [getThreeMonthPerformance](#) | `public SectorUnit getThreeMonthPerformance()` |
| [getYearToDatePerformance](#) | `public SectorUnit getYearToDatePerformance()` |
| [getOneYearPerformance](#) | `public SectorUnit getOneYearPerformance()` |
| [getThreeYearPerformance](#) | `public SectorUnit getThreeYearPerformance()` |
| [getFiveYearPerformance](#) | `public SectorUnit getFiveYearPerformance()` |
| [getTenYearPerformance](#) | `public SectorUnit getTenYearPerformance()` |
| [toString](#) | `public String toString()` |
