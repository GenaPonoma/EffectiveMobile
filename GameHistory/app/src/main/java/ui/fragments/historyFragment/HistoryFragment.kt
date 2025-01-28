package ui.fragments.historyFragment

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import com.example.gamehistory.R
import com.example.gamehistory.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {

    private var countArrayStrings: Int = 0;
    private var sizeArrayStrings: Int = 0;
    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
          return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val resId = R.array.сhapter_1_episode_1
        binding.textVartia.isInvisible = false
        var stringsArray = toConvertStringsFromResources(resources, resId)
        Companion.nextSelectionEpisode(this, stringsArray)
        binding.nextHistory.setOnClickListener {
            Companion.nextSelectionEpisode(this, stringsArray)
        }
    }


    //Функция для перевода string-ресурсов в list<String>
    private fun toConvertStringsFromResources(resources: Resources, resId: Int): List<String> {
        val strings = resources.getStringArray(resId)
        return strings.toList()

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        private fun nextSelectionEpisode(historyFragment: HistoryFragment, stringArray: List<String>) {
            historyFragment.countArrayStrings = stringArray.size - 1
            if (historyFragment.sizeArrayStrings <= historyFragment.countArrayStrings) {
                historyFragment.binding.textHistory.text = stringArray[historyFragment.sizeArrayStrings]
                historyFragment.sizeArrayStrings++;
            }
        }
    }
}