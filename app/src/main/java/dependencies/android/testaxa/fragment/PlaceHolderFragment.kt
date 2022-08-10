package dependencies.android.testaxa.fragment

import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import com.android.common.BaseFragment
import com.android.testaxa.R
import com.android.testaxa.databinding.PlaceHolderListLayoutBinding
import dagger.hilt.android.AndroidEntryPoint
import dependencies.android.testaxa.view_model.PlaceHolderViewModel

@AndroidEntryPoint
class PlaceHolderFragment : BaseFragment<PlaceHolderViewModel, PlaceHolderListLayoutBinding>() {
    override val vm: PlaceHolderViewModel by viewModels()
    override val layoutResourceId: Int = R.layout.place_holder_list_layout

    val adapter = PlaceHolderAdapter()

    override fun initBinding(binding: PlaceHolderListLayoutBinding) {
        super.initBinding(binding)

        binding.rvPlace.adapter = adapter

        observeLiveData()

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchView.clearFocus()
                if (vm.dataPlaceHolder.equals(query)) {
                    adapter.submitData(vm.filter(query))
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    vm.filter(it)
                }?.let {
                    adapter.submitData(it)
                    adapter.query = newText
                }
                return false
            }
        })
    }
}