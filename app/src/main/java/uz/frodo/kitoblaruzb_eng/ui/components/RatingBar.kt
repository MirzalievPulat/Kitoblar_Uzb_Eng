package uz.frodo.kitoblaruzb_eng.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import uz.frodo.kitoblaruzb_eng.R

@Preview
@Composable
fun RatingBarPrev() {
    RatingBar(
        rating = 5.9
    ) {}
}

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Double = 0.0,
    starColor: Color = Color.Yellow,
    starCount: Int = 5,
    onRatingChange: (Double) -> Unit
) {

    var isHalf = (rating % 1) != 0.0

    Row(modifier = modifier) {
        for (index in 1..starCount) {
            Icon(
                modifier = Modifier
                    .clickable { onRatingChange(index.toDouble()) },
                contentDescription = "ratingBar",
                tint = starColor,
                imageVector = if (index <= rating) {
                    ImageVector.vectorResource(id = R.drawable.star_fill_24px)
                } else {
                    if (isHalf) {
                        isHalf = false
                        ImageVector.vectorResource(id = R.drawable.star_half_24px)
                    } else {
                        ImageVector.vectorResource(id = R.drawable.star_24px)
                    }
                }
            )
        }
    }
}