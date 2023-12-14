package cvb.com.br.composestockmarket.data.csv

import com.opencsv.CSVReader
import cvb.com.br.composestockmarket.domain.model.Stock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.InputStreamReader
import javax.inject.Inject

class StockCSVParser @Inject constructor() : CSVParser<Stock> {

    override suspend fun parse(stream: InputStream): List<Stock> {
        val csvReader = CSVReader(InputStreamReader(stream))

        return withContext(Dispatchers.IO) {
            csvReader
                .readAll()
                .drop(1) // Ignore first line -> CSV Title
                .mapNotNull { line ->
                    val symbol = line.getOrNull(0)
                    val name = line.getOrNull(1)
                    val exchange = line.getOrNull(2)

                    Stock(
                        symbol = symbol ?: return@mapNotNull null,
                        name = name ?: return@mapNotNull null,
                        exchange = exchange ?: return@mapNotNull null,
                    )
                }.also {
                    csvReader.close()
                }
        }
    }
}