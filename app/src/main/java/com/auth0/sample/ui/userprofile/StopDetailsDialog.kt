package com.auth0.sample.ui.userprofile

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.auth0.sample.R
import com.auth0.sample.databinding.StopDetailsDialogBinding
import com.auth0.sample.domain.Stop

class StopDetailsDialog : DialogFragment(R.layout.stop_details_dialog) {

    private lateinit var binding: StopDetailsDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val stopName = arguments?.getString(KEY_STOP_NAME, UNKNOWN)
        val stopCode = arguments?.getString(KEY_STOP_CODE, UNKNOWN)

        val context = requireContext()
        binding = StopDetailsDialogBinding.inflate(layoutInflater)
        binding.apply {
            stopNameTv.text = stopName

            stopCodeTv.text = context.getString(
                R.string.stop_code,
                stopCode
            )
        }
        val builder = AlertDialog.Builder(context).apply {
            setView(binding.root)
            setNegativeButton(R.string.close) { _, _ ->
                dialog?.dismiss()
            }
        }
        return builder.create()
    }

    companion object {
        private const val KEY_STOP_NAME = "stop_name"
        private const val KEY_STOP_CODE = "stop_code"
        private const val UNKNOWN = "Unknown"

        fun create(stop: Stop): StopDetailsDialog {
            return StopDetailsDialog().apply {
                arguments = Bundle().apply {
                    putString(KEY_STOP_NAME, stop.name)
                    putString(KEY_STOP_CODE, stop.stopCode)
                }
            }
        }

        fun bundle(stop: Stop): Bundle {
            return Bundle().apply {
                putString(KEY_STOP_NAME, stop.name)
                putString(KEY_STOP_CODE, stop.stopCode)
            }
        }
    }
}
