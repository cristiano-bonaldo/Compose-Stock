package cvb.com.br.composestockmarket.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import cvb.com.br.composestockmarket.data.db.dao.StockDao
import cvb.com.br.composestockmarket.data.db.entity.StockEntity

@Database(
    entities = [ StockEntity::class ],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase: RoomDatabase() {

    companion object {
        val DATABASE_NAME = "AppDataBase"
    }

    abstract val stockDao: StockDao

}