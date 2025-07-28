package com.example.optilens.presentation.view.screens.invoiceDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.optilens.presentation.theme.OptilensTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


// You'll need to define these or import them:
// data class Invoice(...)
// data class InvoiceItem(...)
// class InvoiceDetailsViewModel(...) - This would be how the screen gets data
// sealed interface InvoiceDetailsUiState(...)
// Example Data Classes (place in your domain/data model packages)
data class Invoice(
    val id: String,
    val invoiceNumber: String,
    val customerName: String,
    val issueDate: String,
    val dueDate: String,
    val items: List<InvoiceItem>,
    val subtotal: Double,
    val taxAmount: Double,
    val totalAmount: Double,
    val status: String
)

data class InvoiceItem(
    val description: String,
    val quantity: Int,
    val unitPrice: Double,
    val total: Double
)

// Example UiState (place near your ViewModel or screen)
sealed interface InvoiceDetailsUiState {
    object Loading : InvoiceDetailsUiState
    data class Success(val invoice: Invoice) : InvoiceDetailsUiState
    data class Error(val message: String) : InvoiceDetailsUiState
}

// Example ViewModel (place in your presentation.viewmodel or presentation.screens.invoiceDetails)
// In a real app, inject dependencies like a repository and invoiceId (via SavedStateHandle)
class FakeInvoiceDetailsViewModel(initialState: InvoiceDetailsUiState) { // Simplified for preview
    private var _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<InvoiceDetailsUiState> = _uiState
    // In a real ViewModel, you'd have functions to fetch data

    fun updateState(newState: InvoiceDetailsUiState) {
        _uiState.value = newState
    }
}

@Composable
fun InvoiceDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: FakeInvoiceDetailsViewModel = FakeInvoiceDetailsViewModel(InvoiceDetailsUiState.Loading) // In real app: InvoiceDetailsViewModel
) {
    val uiState by viewModel.uiState.collectAsState()


    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(true) {
        coroutineScope.launch {
            delay(2500)
            viewModel.updateState(
                InvoiceDetailsUiState.Success(
                    Invoice(
                        id = "prev123",
                        invoiceNumber = "PREV-001",
                        customerName = "Preview Customer Inc.",
                        issueDate = "2023-10-01",
                        dueDate = "2023-10-15",
                        items = listOf(
                            InvoiceItem("Development Services", 10, 100.0, 1000.0),
                            InvoiceItem("Consulting", 5, 150.0, 750.0)
                        ),
                        subtotal = 1750.0,
                        taxAmount = 175.0,
                        totalAmount = 1925.0,
                        status = "Paid"
                    )
                )
            )
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp) // Add padding if the parent Scaffold doesn't provide it directly to this content
    ) {
        when (val state = uiState) {
            is InvoiceDetailsUiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            is InvoiceDetailsUiState.Success -> {
                InvoiceContent(invoice = state.invoice, modifier = Modifier.fillMaxSize())
            }

            is InvoiceDetailsUiState.Error -> {
                Text(
                    text = state.message,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun InvoiceContent(invoice: Invoice, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            InvoiceHeader(invoice)
        }
        item {
            InvoiceCustomerDetails(invoice)
        }
        item {
            Text("Items", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
        }
        items(invoice.items) { item ->
            InvoiceListItem(item = item)
            Divider(modifier = Modifier.padding(vertical = 8.dp))
        }
        item {
            InvoiceTotals(invoice)
        }
        item {
            InvoiceStatus(status = invoice.status)
        }
    }
}

@Composable
fun InvoiceHeader(invoice: Invoice) {
    Column {
        Text(
            text = "Invoice #${invoice.invoiceNumber}",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Issued: ${invoice.issueDate} | Due: ${invoice.dueDate}",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun InvoiceCustomerDetails(invoice: Invoice) {
    Card(elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)) {
        Column(modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()) {
            Text("Billed To:", style = MaterialTheme.typography.titleSmall)
            Text(invoice.customerName, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
fun InvoiceListItem(item: InvoiceItem) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(item.description, style = MaterialTheme.typography.bodyLarge)
            Text(
                "Qty: ${item.quantity} @ ${"%.2f".format(item.unitPrice)}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Text(
            text = "$${"%.2f".format(item.total)}",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun InvoiceTotals(invoice: Invoice) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End
    ) {
        TotalRow(label = "Subtotal:", amount = invoice.subtotal)
        TotalRow(label = "Tax:", amount = invoice.taxAmount)
        Spacer(modifier = Modifier.height(8.dp))
        TotalRow(
            label = "Total Amount:",
            amount = invoice.totalAmount,
            isTotal = true
        )
    }
}

@Composable
fun TotalRow(label: String, amount: Double, isTotal: Boolean = false) {
    Row(
        modifier = Modifier.fillMaxWidth(0.6f),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = if (isTotal) MaterialTheme.typography.titleMedium else MaterialTheme.typography.bodyMedium,
            fontWeight = if (isTotal) FontWeight.Bold else FontWeight.Normal
        )
        Text(
            text = "$${"%.2f".format(amount)}",
            style = if (isTotal) MaterialTheme.typography.titleMedium else MaterialTheme.typography.bodyMedium,
            fontWeight = if (isTotal) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Composable
fun InvoiceStatus(status: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text("Status: ", style = MaterialTheme.typography.bodyLarge)
        Text(
            text = status,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            color = when (status.lowercase()) {
                "paid" -> Color(0xFF388E3C) // Green
                "pending" -> Color(0xFFF57C00) // Orange
                "overdue" -> MaterialTheme.colorScheme.error
                else -> MaterialTheme.colorScheme.onSurface
            }
        )
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 780)
@Composable
private fun InvoiceDetails_Success_Preview() {
    val sampleInvoice = Invoice(
        id = "prev123",
        invoiceNumber = "PREV-001",
        customerName = "Preview Customer Inc.",
        issueDate = "2023-10-01",
        dueDate = "2023-10-15",
        items = listOf(
            InvoiceItem("Development Services", 10, 100.0, 1000.0),
            InvoiceItem("Consulting", 5, 150.0, 750.0)
        ),
        subtotal = 1750.0,
        taxAmount = 175.0,
        totalAmount = 1925.0,
        status = "Paid"
    )
    val fakeViewModel =
        FakeInvoiceDetailsViewModel(initialState = InvoiceDetailsUiState.Success(sampleInvoice))
    OptilensTheme { // Replace with your app's theme
        InvoiceDetailsScreen(viewModel = fakeViewModel)
    }
}

@Preview(showBackground = true)
@Composable
private fun InvoiceDetails_Loading_Preview() {
    val fakeViewModel = FakeInvoiceDetailsViewModel(initialState = InvoiceDetailsUiState.Loading)
    OptilensTheme {
        InvoiceDetailsScreen(viewModel = fakeViewModel)
    }
}

@Preview(showBackground = true)
@Composable
private fun InvoiceDetails_Error_Preview() {
    val fakeViewModel =
        FakeInvoiceDetailsViewModel(initialState = InvoiceDetailsUiState.Error("Could not load invoice."))
    OptilensTheme {
        InvoiceDetailsScreen(viewModel = fakeViewModel)
    }
}

@Preview
@Composable
private fun InvoiceDetails_prev() {
    InvoiceDetailsScreen(

    )
}