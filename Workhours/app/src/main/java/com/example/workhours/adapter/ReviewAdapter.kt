import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.workhours.R
import com.example.workhours.databinding.MyListViewCardAdapterBinding
import com.example.workhours.room.MonthlySummary
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ReviewAdapter(
    private val arr: Array<String>,
    private var monthlySummaries: Flow<List<MonthlySummary>>,
    private val lifecycleOwner: LifecycleOwner
) :
    RecyclerView.Adapter<MyViewHolder>() {

    private var summaries: List<MonthlySummary> = List(12) {
        MonthlySummary(
            id = it,
            year = 2024,
            month = arr[it],
            shiftCount = 0,
            totalHours = 0.0,
            totalSalary = 0.0
        )
    }

    init {
        lifecycleOwner.lifecycleScope.launch {
            monthlySummaries.collect { newSummaries ->
                newSummaries.forEach { newSummary ->
                    summaries = summaries.map { summary ->
                        if (summary.month == newSummary.month) newSummary else summary
                    }
                }
                notifyDataSetChanged()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = MyListViewCardAdapterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val summary = summaries[position]
        holder.binding.headrBouns.text = summary.month
        holder.binding.numberOfClipsHours.text = "${summary.shiftCount} ч"
        holder.binding.headerCoo.text = "${summary.totalSalary} ₽"
        holder.binding.iconButton.setOnClickListener {
            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.action_navigation_review_to_moreDetailed)
        }
    }

    override fun getItemCount(): Int {
        return summaries.size
    }
}

class MyViewHolder(val binding: MyListViewCardAdapterBinding) :
    RecyclerView.ViewHolder(binding.root)
