package cvb.com.br.composestockmarket.data.mapper

import cvb.com.br.composestockmarket.data.db.entity.StockEntity
import cvb.com.br.composestockmarket.domain.model.Stock

fun Stock.toStockEntity() =
    StockEntity(
        name = this.name,
        symbol = this.symbol,
        exchange = this.exchange
    )

fun StockEntity.toStock() =
    Stock(
        name = this.name,
        symbol = this.symbol,
        exchange = this.exchange
    )