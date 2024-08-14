package ui.fragments.homeFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.example.gamehistory.R
import com.example.gamehistory.databinding.FragmentHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ui.fragments.historyFragment.HistoryFragment

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.fade)

        binding.goHisttory.setOnClickListener {

            val alertDialog = MaterialAlertDialogBuilder(requireContext())
                .setTitle("Внимание!")
                .setMessage("Все что есть в данной истории ни как не связанно с реальной жизни")
                .setPositiveButton("Ок") { dialog, _ ->
                    dialog.dismiss() // Закрыть диалоговое окно
                    replaceFragment(HistoryFragment())
                }
//                .setNegativeButton("No") { dialog, _ ->
//                    dialog.cancel() // Отменить диалоговое окно
//                }
                .create()
            alertDialog.show()

        }

    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = parentFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(
            R.id.nav_host_fragment,
            fragment
        )
        transaction.commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}