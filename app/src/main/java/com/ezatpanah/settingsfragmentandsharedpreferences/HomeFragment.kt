package com.ezatpanah.settingsfragmentandsharedpreferences

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.ezatpanah.settingsfragmentandsharedpreferences.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.apply {
            fabSettings.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_settingsFragment)
            }

            loadSettings()
        }

    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.N)
    private fun loadSettings() {
        val sp = PreferenceManager.getDefaultSharedPreferences(context)
        val signature = sp.getString("signature", "")
        val defaultReplyAction = sp.getString("reply", "")
        val sync = sp.getBoolean("sync", true)
        val notifications = sp.getBoolean("notifications", true)
        val volume = sp.getInt("volume_notifications", 0)
        binding.apply{
            tvSignature.text = "Signature: $signature"
            tvReply.text = "Default reply: $defaultReplyAction"
            tvSync.text = "Sync: $sync"
            tvNotifications.text = "Disable notifications: $notifications"

            pbVolume.setProgress(volume, true)

        }


    }

}