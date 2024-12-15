package app.cleanmeter.target.desktop.ui.components.section

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import app.cleanmeter.core.designsystem.LocalColorScheme
import app.cleanmeter.target.desktop.ui.ColorTokens.MutedGray
import app.cleanmeter.target.desktop.ui.components.SectionTitle
import app.cleanmeter.target.desktop.ui.components.Toggle

@Composable
fun ToggleSection(
    title: String,
    isEnabled: Boolean,
    onSwitchToggle: (Boolean) -> Unit,
    content: @Composable () -> Unit
) = Column(
    modifier = Modifier
        .animateContentSize()
        .background(LocalColorScheme.current.background.surfaceRaised, RoundedCornerShape(12.dp))
        .padding(20.dp),
    verticalArrangement = Arrangement.spacedBy(20.dp)
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SectionTitle(title = title)
        Toggle(
            checked = isEnabled,
            onCheckedChange = onSwitchToggle
        )
    }

    if (isEnabled) {
        content()
    }
}