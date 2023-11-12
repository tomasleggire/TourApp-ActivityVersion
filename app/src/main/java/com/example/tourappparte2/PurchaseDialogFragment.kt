package com.example.tourappparte2

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.example.tourappparte2.data.User
import com.example.tourappparte2.databinding.PurchaseDialogFragmentBinding

class PurchaseDialogFragment(private val isMoneyEnough: Boolean, private val packageName: String, private val actualUser: User): DialogFragment() {

    private var _binding: PurchaseDialogFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = PurchaseDialogFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Segun el condicional, arma de una manera u otra el dialogFragment
        val dialogData = if (isMoneyEnough) {
            DialogData(
                icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_compra_exitosa),
                title = "¡Compra exitosa!",
                subtitle = "Compraste $packageName.",
                buttonText = "A mis compras",
                buttonClickListener = View.OnClickListener {
                    val intent = Intent(context, PurchasesActivity::class.java)
                    intent.putExtra("USER_ID", actualUser.id)
                    startActivity(intent)
                    requireActivity().finish()
                    dismiss()
                }
            )
        } else {
            DialogData(
                icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_compra_fallida),
                title = "¡Oh no!",
                subtitle = "No tienes suficiente dinero",
                buttonText = "Volver",
                buttonClickListener = View.OnClickListener {
                    requireActivity().finish()
                    dismiss()
                }
            )
        }

        binding.dfImage.setImageDrawable(dialogData.icon)
        binding.dfTitle.text = dialogData.title
        binding.dfSubtitle.text = dialogData.subtitle
        binding.dgBtn.text = dialogData.buttonText
        binding.dgBtn.setOnClickListener(dialogData.buttonClickListener)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    data class DialogData(
        val icon: Drawable?,
        val title: String,
        val subtitle: String,
        val buttonText: String,
        val buttonClickListener: View.OnClickListener
    )
}