package ca.finalfive.strangercommons.composables.utils

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ca.finalfive.strangercommons.ui.theme.OutlineTextFieldColor

/**
 * CustomTextField - made our own text field
 * @param value - user's information
 * @param setValue - changing the information based on the value user enters
 * @param label - the text field title
 * @param iconID - The Icons of the text field
 * @param modifier - To modify the text field
 */
@Composable
fun CustomTextField(
    value: String,
    setValue: (String)->Unit,
    label: String,
    iconID: Int,
    modifier: Modifier = Modifier
){
    // text field
    TextField(
        // value of the text field
        value = value,
        // changing the value
        onValueChange = setValue,
        // place holder of the text field
        placeholder = {
            Text(text = "Enter $label")
        },
        // title of the text field
        label = {
            Text(text = label)
        },
        // icon of the text field
        leadingIcon = {
            Icon(
                painter = painterResource(id = iconID),
                contentDescription = null,
                modifier = Modifier.size(35.dp),
                tint = Color.White
            )
        },
        // colors of the text field such as background, texts and etc.
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = OutlineTextFieldColor,
            textColor = Color.White,
            placeholderColor = Color.White,
            unfocusedLabelColor = Color.White
        ),
        modifier = modifier,
        // border radius 
        shape = RoundedCornerShape(10.dp),
    )
}