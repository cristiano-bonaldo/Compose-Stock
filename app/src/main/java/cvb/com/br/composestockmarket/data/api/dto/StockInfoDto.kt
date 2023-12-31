package cvb.com.br.composestockmarket.data.api.dto


import com.squareup.moshi.Json

data class StockInfoDto(
    @field:Json(name = "Address")
    val address: String?,
    @field:Json(name = "AnalystTargetPrice")
    val analystTargetPrice: String?,
    @field:Json(name = "AssetType")
    val assetType: String?,
    @field:Json(name = "Beta")
    val beta: String?,
    @field:Json(name = "BookValue")
    val bookValue: String?,
    @field:Json(name = "CIK")
    val cIK: String?,
    @field:Json(name = "Country")
    val country: String?,
    @field:Json(name = "Currency")
    val currency: String?,
    @field:Json(name = "50DayMovingAverage")
    val dayMovingAverage50: String?,
    @field:Json(name = "200DayMovingAverage")
    val dayMovingAverage200: String?,
    @field:Json(name = "Description")
    val description: String?,
    @field:Json(name = "DilutedEPSTTM")
    val dilutedEPSTTM: String?,
    @field:Json(name = "DividendDate")
    val dividendDate: String?,
    @field:Json(name = "DividendPerShare")
    val dividendPerShare: String?,
    @field:Json(name = "DividendYield")
    val dividendYield: String?,
    @field:Json(name = "EBITDA")
    val eBITDA: String?,
    @field:Json(name = "EPS")
    val ePS: String?,
    @field:Json(name = "EVToEBITDA")
    val eVToEBITDA: String?,
    @field:Json(name = "EVToRevenue")
    val eVToRevenue: String?,
    @field:Json(name = "ExDividendDate")
    val exDividendDate: String?,
    @field:Json(name = "Exchange")
    val exchange: String?,
    @field:Json(name = "FiscalYearEnd")
    val fiscalYearEnd: String?,
    @field:Json(name = "ForwardPE")
    val forwardPE: String?,
    @field:Json(name = "GrossProfitTTM")
    val grossProfitTTM: String?,
    @field:Json(name = "Industry")
    val industry: String?,
    @field:Json(name = "LatestQuarter")
    val latestQuarter: String?,
    @field:Json(name = "MarketCapitalization")
    val marketCapitalization: String?,
    @field:Json(name = "Name")
    val name: String?,
    @field:Json(name = "OperatingMarginTTM")
    val operatingMarginTTM: String?,
    @field:Json(name = "PEGRatio")
    val pEGRatio: String?,
    @field:Json(name = "PERatio")
    val pERatio: String?,
    @field:Json(name = "PriceToBookRatio")
    val priceToBookRatio: String?,
    @field:Json(name = "PriceToSalesRatioTTM")
    val priceToSalesRatioTTM: String?,
    @field:Json(name = "ProfitMargin")
    val profitMargin: String?,
    @field:Json(name = "QuarterlyEarningsGrowthYOY")
    val quarterlyEarningsGrowthYOY: String?,
    @field:Json(name = "QuarterlyRevenueGrowthYOY")
    val quarterlyRevenueGrowthYOY: String?,
    @field:Json(name = "ReturnOnAssetsTTM")
    val returnOnAssetsTTM: String?,
    @field:Json(name = "ReturnOnEquityTTM")
    val returnOnEquityTTM: String?,
    @field:Json(name = "RevenuePerShareTTM")
    val revenuePerShareTTM: String?,
    @field:Json(name = "RevenueTTM")
    val revenueTTM: String?,
    @field:Json(name = "Sector")
    val sector: String?,
    @field:Json(name = "SharesOutstanding")
    val sharesOutstanding: String?,
    @field:Json(name = "Symbol")
    val symbol: String?,
    @field:Json(name = "TrailingPE")
    val trailingPE: String?,
    @field:Json(name = "52WeekHigh")
    val weekHigh: String?,
    @field:Json(name = "52WeekLow")
    val weekLow: String?
)