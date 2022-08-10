package dependencies.android.testaxa.fragment

fun PlaceHolderFragment.observeLiveData() = with(vm) {

    observeResponseData(
        dataPlaceHolder,
        success = {
            adapter.submitData(it)
        },
        loading = {},
        error = {}
    )
}