package com.pustovit.pdp.character

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import coil.ImageLoader
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.pustovit.pdp.character.databinding.FragmentCharacterBinding
import com.pustovit.pdp.character.di.CharacterComponentHolder
import com.pustovit.pdp.character.di.ViewModelFactory
import com.pustovit.pdp.character.mvi.CharacterViewState
import com.pustovit.pdp.character.navigation.Screens
import com.pustovit.pdp.character.summary.SummaryTitle
import com.pustovit.pdp.common_ui.ui.*
import io.reactivex.rxkotlin.addTo
import timber.log.Timber
import javax.inject.Inject

class CharacterFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var imageLoader: ImageLoader

    private val viewModel by baseViewModels<CharacterViewModel> {
        viewModelFactory
    }

    private var binding: FragmentCharacterBinding? = null

    private val compositeDisposable by CompositeDisposableDelegate()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        CharacterComponentHolder.getComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = with(FragmentCharacterBinding.inflate(layoutInflater)) {
        binding = this
        this.root
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt(ARG_KEY)?.let {
            viewModel.loadCharacter(it)
            binding?.let {
                initViews(it, savedInstanceState)
            }
            observeViewState()
        }
    }


    private fun initViews(binding: FragmentCharacterBinding, savedInstanceState: Bundle?) {
        binding.comicsButton.setOnClickListener { onComicsButtonClick() }
        binding.seriesButton.setOnClickListener { onSeriesButtonClick() }
        binding.storiesButton.setOnClickListener { onStoriesButtonClick() }
    }

    private fun observeViewState() {
        viewModel.viewState
            .doOnNext(::handleViewState)
            .subscribe().addTo(compositeDisposable)
    }

    private fun handleViewState(state: CharacterViewState) {
        state.apply {
            handleViewStateError(viewStateError)
            binding?.let {
                it.progressBar.visibility = if (loading) View.VISIBLE else View.GONE

                if (character.thumbnail.isValid) {

                    val request = ImageRequest.Builder(requireContext())
                        .data(character.thumbnail.url)
                        .transformations(CircleCropTransformation())
                        .target(it.characterImageView)
                        .error(CoreDrawable.ic_person_24)
                        .build()
                    imageLoader.enqueue(request)
                }
                it.nameTextView.text = character.name
                it.descriptionsTextView.text = character.description

                it.comicsButton.text = getString(CoreString.comics, comicsCount)
                it.seriesButton.text = getString(CoreString.series, seriesCount)
                it.storiesButton.text = getString(CoreString.stories, storiesCount)
            }

        }
    }

    private fun onComicsButtonClick() {
        val comics = viewModel.currentViewState.character.comics
        router().navigateTo(Screens.summaryScreen(SummaryTitle.Comics, comics))
    }

    private fun onSeriesButtonClick() {
        val series = viewModel.currentViewState.character.series
        router().navigateTo(Screens.summaryScreen(SummaryTitle.Series, series))
    }

    private fun onStoriesButtonClick() {
        val stories = viewModel.currentViewState.character.stories
        router().navigateTo(Screens.summaryScreen(SummaryTitle.Stories, stories))
    }

    companion object {
        private const val ARG_KEY = "character"

        fun newInstance(characterId: Int): CharacterFragment {
            return CharacterFragment().apply {
                arguments = bundleOf(ARG_KEY to characterId)
            }
        }
    }
}