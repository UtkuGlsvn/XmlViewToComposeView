package com.utkuglsvn.xmlviewtocomposeview

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.utkuglsvn.xmlviewtocomposeview.ui.theme.XmlViewToComposeViewTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            XmlViewToComposeViewTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    XmlToButton("Android")
                    XmlToTextview()
                    XmlToImageView()
                    XmlToGridView()
                    XmlToHorizontalLine()
                    XmlToLinearLayoutHorizontal()
                    XmlToVerticalLine()
                    XmlToLinearLayoutVertical()
                    MyDialog()
                    Spacer(modifier = Modifier.height(16.dp))
                    RecyclerViewContent(sampleData)
                }
            }
        }
    }
}

@Composable
fun XmlToButton(name: String, context: Context = LocalContext.current) {
    Button(onClick = {
        Toast.makeText(context, "Hello, $name!", Toast.LENGTH_SHORT).show()
    }) {
        Text("Click Me")
    }
}

@Composable
fun XmlToTextview() {
    Text(
        text = "Hello World",
        fontSize = 18.sp,
        color = Color.Blue,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Italic
    )
}

@Composable
fun XmlToImageView() {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_foreground),
        contentDescription = null,  // Use appropriate content descriptions for accessibility
        modifier = Modifier
            .clip(CircleShape)  // Apply a circular clip to the image
        ,
        colorFilter = ColorFilter.tint(Color.Red)
    )

}

@Composable
fun XmlToGridView() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        content = {
            items(4) { index ->
                Text("Item ${index + 1}")
            }
        }
    )

}

@Composable
fun XmlToLinearLayoutHorizontal() {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text("Horizontal Linear layout")
        Text("Horizontal Linear layout")
    }
}


@Composable
fun XmlToLinearLayoutVertical() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text("Vertical Linear layout")
        Text("Vertical Linear layout")
    }
}

@Composable
fun XmlToVerticalLine() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Divider(
            modifier = Modifier
                .align(Alignment.Center)
                .height(100.dp)
                .width(1.dp),
            color = Color.Black
        )
    }
}

@Composable
fun XmlToHorizontalLine() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp, top = 10.dp, bottom = 10.dp)
    ) {
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp),
            color = Color.Black
        )
    }
}

val sampleData = listOf(
    RecyclerViewItem("Item 1", 1),
    RecyclerViewItem("Item 2", 2),
    RecyclerViewItem("Item 3", 3),
    RecyclerViewItem("Item 3", 4),
    RecyclerViewItem("Item 3", 5)
)


@Composable
fun RecyclerViewContent(items: List<RecyclerViewItem>) {
    LazyColumn(contentPadding = PaddingValues(10.dp)) {
        items(items) { item ->
            RecyclerViewItem(item)
        }
    }
}

@Composable
fun RecyclerViewItem(item: RecyclerViewItem) {
    Surface(
        color = Color.Gray, modifier = Modifier
            .padding(5.dp)
            .fillMaxSize()
    ) {
        Column {
            Text(text = item.name, style = MaterialTheme.typography.bodyMedium)
            Text(text = item.index.toString(), style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun MyDialog() {
    val showDialog = remember { mutableStateOf(false) }

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { Text("Dialog Title") },
            text = { Text("This is a Jetpack Compose dialog!") },
            confirmButton = {
                Button(
                    onClick = { showDialog.value = false },
                ) {
                    Text("OK")
                }
            }
        )
    }

    Button(onClick = { showDialog.value = true }) {
        Text("Show Dialog")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    XmlViewToComposeViewTheme {
        XmlToTextview()
    }
}


