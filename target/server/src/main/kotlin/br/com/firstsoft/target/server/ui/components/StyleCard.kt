package br.com.firstsoft.target.server.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.firstsoft.target.server.ui.ColorTokens.AlmostVisibleGray
import br.com.firstsoft.target.server.ui.ColorTokens.BarelyVisibleGray
import br.com.firstsoft.target.server.ui.ColorTokens.DarkGray
import br.com.firstsoft.target.server.ui.overlay.conditional

@Composable
internal fun StyleCard(
    label: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    customLabel: @Composable (BoxScope.() -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit,
) = Column(
    modifier = modifier
        .aspectRatio(1.15f)
        .background(Color.White, RoundedCornerShape(8.dp))
        .conditional(
            predicate = isSelected,
            ifTrue = { border(2.dp, DarkGray, RoundedCornerShape(8.dp)) },
            ifFalse = { border(1.dp, AlmostVisibleGray, RoundedCornerShape(8.dp)) }
        )
        .padding(4.dp)
        .clickable { onClick() }
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16 / 9f)
            .background(BarelyVisibleGray, RoundedCornerShape(4.dp))
            .padding(8.dp),
        content = content,
        contentAlignment = Alignment.Center
    )

    Box(modifier = Modifier.weight(1f).fillMaxWidth().padding(12.dp)) {
        if (customLabel != null) {
            customLabel()
        } else {
            Text(
                text = label,
                fontSize = 14.sp,
                color = DarkGray,
                lineHeight = 0.sp,
                fontWeight = FontWeight(550),
                letterSpacing = 0.14.sp,
                modifier = Modifier.align(Alignment.CenterStart)
            )
        }
    }
}