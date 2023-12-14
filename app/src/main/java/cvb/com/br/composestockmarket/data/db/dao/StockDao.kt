package cvb.com.br.composestockmarket.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import cvb.com.br.composestockmarket.data.db.entity.StockEntity

@Dao
interface StockDao {
    @Insert
    suspend fun insertListStock(listStock: List<StockEntity>)

    @Query("DELETE FROM stock")
    suspend fun deleteAllStock()

    @Query("SELECT * FROM stock WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%' OR UPPER(symbol) = UPPER(:query)")
    suspend fun queryListStockEntity(query: String): List<StockEntity>

    @Query("SELECT * FROM stock")
    suspend fun getListStockEntity(): List<StockEntity>
}