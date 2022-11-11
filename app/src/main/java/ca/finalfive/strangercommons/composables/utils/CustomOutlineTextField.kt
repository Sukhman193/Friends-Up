package ca.finalfive.strangercommons.composables.utils

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ca.finalfive.strangercommons.R
import ca.finalfive.strangercommons.ui.theme.OutlineTextFieldColor

@Composable
fun CustomOutlineTextField(
    value: String,
    setValue: (String)->Unit,
    label: String,
    iconID: Int,
    modifier: Modifier = Modifier
){
    OutlinedTextField(
        value = value,
        onValueChange = setValue,
        label = {
            Text(text = label)
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = iconID),
                contentDescription = null,
                modifier = Modifier.size(40.dp),
                tint = Color.White
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = OutlineTextFieldColor
        ),
        modifier = modifier,
        shape = RoundedCornerShape(10.dp)
    )

}