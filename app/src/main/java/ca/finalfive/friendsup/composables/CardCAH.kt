package ca.finalfive.friendsup.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import ca.finalfive.friendsup.R

@Composable
fun CardCAH(){
    Column() {
        //this spacer is where we will add the other premade components
        Spacer(modifier = Modifier.height(200.dp))
        Box(modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .height(400.dp)){

            //answer card #1 -> top left of screen
            Card(modifier = Modifier
                .height(175.dp)
                .width(135.dp)
                .align(Alignment.TopStart),
                shape = RoundedCornerShape(8.dp),
                backgroundColor = colorResource(id = R.color.white),
                elevation = 10.dp
            ){
                Text("White Card", modifier = Modifier.padding(20.dp))
            }

            //answer card #2 -> top right of screen
            Card(modifier = Modifier
                .height(175.dp)
                .width(135.dp)
                .align(Alignment.TopEnd),
                shape = RoundedCornerShape(8.dp),
                backgroundColor = colorResource(id = R.color.white),
                elevation = 10.dp
            ){
                Text("White Card", modifier = Modifier.padding(20.dp))
            }

            //answer card #3 -> bottom left of screen
            Card(modifier = Modifier
                .height(175.dp)
                .width(135.dp)
                .align(Alignment.BottomStart),
                shape = RoundedCornerShape(8.dp),
                backgroundColor = colorResource(id = R.color.white),
                elevation = 10.dp
            ){
                Text("White Card", modifier = Modifier.padding(20.dp))
            }

            //answer card #4 -> bottom right of screen
            Card(modifier = Modifier
                .height(175.dp)
                .width(135.dp)
                .align(Alignment.BottomEnd),
                shape = RoundedCornerShape(8.dp),
                backgroundColor = colorResource(id = R.color.white),
                elevation = 10.dp,
            ){
                Text("White Card", modifier = Modifier.padding(20.dp))
            }

            //the black question card that will display in the middle
            Card(modifier = Modifier
                .zIndex(100F)
                .align(Alignment.Center)
                .height(175.dp)
                .width(120.dp),
                shape = RoundedCornerShape(8.dp),
                backgroundColor = colorResource(id = R.color.black),
                elevation = 10.dp
            ){
                Text("Black Card", color = colorResource(id = R.color.white), modifier = Modifier.padding(10.dp))
            }
        }
    }

}

