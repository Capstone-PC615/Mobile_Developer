package com.capstone.trashsortapp.ui.setting

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.capstone.trashsortapp.R
import com.capstone.trashsortapp.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {
    private lateinit var textLanguage: TextView
    private lateinit var switchTheme: Switch

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentSettingBinding = FragmentSettingBinding.inflate(
            inflater, container, false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textLanguage = view.findViewById(R.id.text_language)
        switchTheme= view.findViewById(R.id.switch_theme)

        val ivLanguageSettings: ImageView = view.findViewById(R.id.ivLanguageSettings)
        ivLanguageSettings.setOnClickListener {
            val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(intent)
        }

        switchTheme = view.findViewById(R.id.switch_theme)
        switchTheme.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                enableDarkMode()
            } else {
                disableDarkMode()
            }
        }

        updateLanguage()
    }

    private fun enableDarkMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }

    private fun disableDarkMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_LANGUAGE_SETTINGS) {
            updateLanguage()
        }
    }

    private fun updateLanguage() {
        val currentLocale = resources.configuration.locale
        val currentLanguage = currentLocale.language

        val languageText = when (currentLanguage) {
            "in" -> getString(R.string.language_indonesian)
            else -> getString(R.string.language_english)
        }

        textLanguage.text = languageText
    }

    companion object {
        private const val REQUEST_LANGUAGE_SETTINGS = 1
    }
}