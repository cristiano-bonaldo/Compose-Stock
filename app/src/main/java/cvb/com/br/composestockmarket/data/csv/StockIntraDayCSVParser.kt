package cvb.com.br.composestockmarket.data.csv

import com.opencsv.CSVReader
import cvb.com.br.composestockmarket.data.api.dto.StockIntraDayDto
import cvb.com.br.composestockmarket.data.mapper.toStockIntraDay
import cvb.com.br.composestockmarket.domain.model.StockIntraDay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.InputStreamReader
import java.time.LocalDate
import javax.inject.Inject

class StockIntraDayCSVParser @Inject constructor() : CSVParser<StockIntraDay> {

    override suspend fun parse(stream: InputStream): List<StockIntraDay> {
        val csvReader = CSVReader(InputStreamReader(stream))

        return withContext(Dispatchers.IO) {
            csvReader
                .readAll()
                .drop(1) // Ignore first line -> CSV Title
                .mapNotNull { line ->
                    val timeStamp = line.getOrNull(0)
                    val close = line.getOrNull(4)

                    val stockIntraDayDto = StockIntraDayDto(
                        timestamp = timeStamp ?: return@mapNotNull null,
                        close = close ?: return@mapNotNull null
                    )

                    stockIntraDayDto.toStockIntraDay()
                }.filter { stockIntraDay ->
                    stockIntraDay.dateTime.dayOfMonth == LocalDate.now().minusDays(1).dayOfMonth
                }.sortedBy { stockIntraDay ->
                    stockIntraDay.dateTime.hour
                }
        }.also {
            csvReader.close()
        }
    }
}
