package cvb.com.br.composestockmarket.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stock")
data class StockEntity(
    @PrimaryKey val id: Long? = null,
    val symbol: String,
    val name: String,
    val exchange: String
)