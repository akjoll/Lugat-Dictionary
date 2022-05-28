package kg.lugatdictionary.ui.launch


//class LaunchFragment : BaseVMFragment<FragmentLauncherBinding, LaunchVM>() {
//    override val viewModel: LaunchVM by viewModel()
//
//    override fun inflateBinding(inflater: LayoutInflater) =
//        FragmentLauncherBinding.inflate(inflater)
//
//    override fun init() {
//        initObservers()
//        initRequests()
//    }
//
//    private fun initRequests() = with(viewModel) {
//        fetchWords()
//    }
//
//    private fun initObservers() = with(viewModel) {
//        withLifecycleScope(words) {
//            saveWords(it)
//        }
//
//        withLifecycleScope(successWordUpdate) {
//            findNavController().navigate(LaunchFragmentDirections.actionLaunchFragmentToSearch())
//        }
//
//    }
//}