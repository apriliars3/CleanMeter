package app.cleanmeter.target.desktop.ui.settings.tabs

import ClearButton
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.TooltipArea
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import app.cleanmeter.core.designsystem.LocalColorScheme
import app.cleanmeter.core.designsystem.LocalTypography
import app.cleanmeter.core.os.win32.WinRegistry
import app.cleanmeter.core.os.PREFERENCE_START_MINIMIZED
import app.cleanmeter.core.os.PreferencesRepository
import app.cleanmeter.target.desktop.model.OverlaySettings
import app.cleanmeter.target.desktop.ui.components.CheckboxWithLabel
import app.cleanmeter.target.desktop.ui.components.StyleCard
import app.cleanmeter.target.desktop.ui.components.dropdown.DropdownMenu
import app.cleanmeter.target.desktop.ui.components.section.Section
import app.cleanmeter.target.desktop.ui.settings.FooterUi
import app.cleanmeter.target.desktop.ui.settings.SettingsEvent

@Composable
fun AppSettingsUi(
    overlaySettings: OverlaySettings,
    onEvent: (SettingsEvent) -> Unit
) = Column(
    modifier = Modifier.fillMaxSize().padding(top = 20.dp).verticalScroll(rememberScrollState()),
    verticalArrangement = Arrangement.spacedBy(16.dp)
) {
    Section(title = "GENERAL") {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            startWithWindowsCheckbox()
            startMinimizedCheckbox()
            ClearButton(label = "Clear app preferences", textColor = LocalColorScheme.current.text.heading) {
                PreferencesRepository.clear()
            }
        }
    }

    Section(title = "APPEARANCE") {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            StyleCard(
                label = "Light",
                isSelected = !overlaySettings.isDarkTheme,
                modifier = Modifier.weight(.5f).height(210.dp),
                onClick = { onEvent(SettingsEvent.DarkThemeToggle(false)) },
                content = {
                    Image(
                        painter = painterResource("icons/light_mode.png"),
                        contentDescription = "light mode image"
                    )
                }
            )

            StyleCard(
                label = "Dark",
                isSelected = overlaySettings.isDarkTheme,
                modifier = Modifier.weight(.5f).height(210.dp),
                onClick = { onEvent(SettingsEvent.DarkThemeToggle(true)) },
                content = {
                    Image(
                        painter = painterResource("icons/dark_mode.png"),
                        contentDescription = "dark mode image",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            )

//            StyleCard(
//                label = "System",
//                isSelected = !overlaySettings.isDarkTheme,
//                modifier = Modifier.weight(.3f).aspectRatio(1.15f),
//                onClick = { onEvent(SettingsEvent.DarkThemeToggle(true)) },
//                content = {
//                    Image(
//                        painter = painterResource("icons/dynamic_mode.png"),
//                        contentDescription = "dynamic mode image",
//                        modifier = Modifier.align(Alignment.Center)
//                    )
//                }
//            )
        }
    }

    Section(title = "RECORDING") {
        val options = listOf("33", "50", "100", "250", "300", "350", "400", "500")
        DropdownMenu(
            label = "Polling Rate:",
            disclaimer = "The interval in milliseconds the app will update data. Be mindful, this can impact performance!",
            options = options,
            selectedIndex = options.indexOf(overlaySettings.pollingRate.toString()),
            onValueChanged = { onEvent(SettingsEvent.PollingRateSelect(options[it].toLong())) },
            modifier = Modifier.padding(top = 8.dp)
        )
    }

    FooterUi(modifier = Modifier)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun startWithWindowsCheckbox() {
    var state by remember { mutableStateOf(WinRegistry.isAppRegisteredToStartWithWindows()) }

    LaunchedEffect(Unit) {
        if (state) {
            WinRegistry.removeAppFromStartWithWindows()
        }
    }

    TooltipArea(
        delayMillis = 0,
        tooltip = {
            Text(
                text = "Temporarily disabled.",
                style = LocalTypography.current.labelM,
                color = LocalColorScheme.current.text.heading,
            )
        }) {
        CheckboxWithLabel(
            label = "Start with Windows",
            checked = state,
            enabled = false,
            onCheckedChange = { value ->
                state = value
                if (value) {
                    WinRegistry.registerAppToStartWithWindows()
                } else {
                    WinRegistry.removeAppFromStartWithWindows()
                }
            }
        )
    }
}

@Composable
private fun startMinimizedCheckbox() {
    var state by remember { mutableStateOf(PreferencesRepository.getPreferenceBoolean(PREFERENCE_START_MINIMIZED)) }
    CheckboxWithLabel(
        label = "Start Minimized",
        checked = state,
        onCheckedChange = { value ->
            state = value
            PreferencesRepository.setPreferenceBoolean(PREFERENCE_START_MINIMIZED, value)
        },
    )
}

@Composable
private fun darkThemeCheckbox(overlaySettings: OverlaySettings, onEvent: (SettingsEvent) -> Unit) {
    CheckboxWithLabel(
        label = "Dark Theme",
        checked = overlaySettings.isDarkTheme,
        onCheckedChange = { value ->
            onEvent(SettingsEvent.DarkThemeToggle(value))
        },
    )
}
