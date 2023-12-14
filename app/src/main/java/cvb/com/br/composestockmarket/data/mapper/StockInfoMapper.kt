package cvb.com.br.composestockmarket.data.mapper

import cvb.com.br.composestockmarket.data.api.dto.StockInfoDto
import cvb.com.br.composestockmarket.data.db.entity.StockEntity
import cvb.com.br.composestockmarket.domain.model.StockInfo

fun StockInfoDto.toStockInfo() =
    StockInfo(
        symbol = this.symbol ?: "",
        name = this.name ?: "",
        description = this.description ?: "",
        country = this.country ?: "",
        industry = this.industry ?: ""
    )
