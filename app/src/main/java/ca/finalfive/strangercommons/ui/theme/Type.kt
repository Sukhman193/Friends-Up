package ca.finalfive.strangercommons.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ca.finalfive.strangercommons.R

// Font variable for "Comfortaa"
val comfortaa = FontFamily(
    // Normal Font weight
    Font(R.font.comfortaa_regular, weight = FontWeight.Normal),
    // Light Font weight
    Font(R.font.comfortaa_light, weight = FontWeight.Light),
    // Bold Font weight
    Font(R.font.comfortaa_bold, weight = FontWeight.Bold)
)

// Font variable for "Hello Valentina"
val hello_valentina = FontFamily(
    // Normal Font weight (Only comes with one font weight)
    Font(R.font.hello_valentina, weight = FontWeight.Normal)
)

// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        fontFamily = comfortaa,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    h2 = TextStyle(
        fontFamily = comfortaa,
        fontWeight = FontWeight.Light,
        fontSize = 16.sp
    ),
    h3 = TextStyle(
        fontFamily = comfortaa,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    h4 = TextStyle(
        fontFamily = hello_valentina,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)