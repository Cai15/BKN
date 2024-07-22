package com.example.bkn.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bkn.R
import com.example.bkn.databinding.FragmentFavoritesBinding
import com.example.bkn.domain.Book
import com.example.bkn.view.MainActivity
import com.example.bkn.view.rv_adapters.BookListRecyclerAdapter
import com.example.bkn.view.rv_adapters.TopSpacingItemDecoration


class FavoritesFragment : Fragment(R.layout.fragment_favorites) {
    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var BooksAdapter: BookListRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Получаем список при транзакции фрагмента
        val favoritesList: List<Book> = emptyList()

        /*AnimationHelper.performFragmentCircularRevealAnimation(
            binding.favoritesFragmentRoot,
            requireActivity(),
            2
        )*/

        binding.favoritesRecycler.apply {
            BooksAdapter =
                BookListRecyclerAdapter(object : BookListRecyclerAdapter.OnItemClickListener {
                    override fun click(Book: Book) {
                        (requireActivity() as MainActivity).launchDetailsFragment(Book)
                    }
                })
            //Присваиваем адаптер
            adapter = BooksAdapter
            //Присвои layoutmanager
            layoutManager = LinearLayoutManager(requireContext())
            //Применяем декоратор для отступов
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)
        }
        //Кладем нашу БД в RV
        BooksAdapter.addItems(favoritesList)
    }
}
