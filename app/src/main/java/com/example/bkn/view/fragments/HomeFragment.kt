package com.example.bkn.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bkn.databinding.FragmentHomeBinding
import com.example.bkn.domain.Book
import com.example.bkn.view.MainActivity
import com.example.bkn.view.rv_adapters.BookListRecyclerAdapter
import com.example.bkn.view.rv_adapters.TopSpacingItemDecoration
import com.example.bkn.viewmodel.HomeFragmentViewModel
import java.util.Locale


class HomeFragment : Fragment() {
    private val viewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(HomeFragmentViewModel::class.java)
    }
    private lateinit var binding: FragmentHomeBinding
    private lateinit var BooksAdapter: BookListRecyclerAdapter

    private var BooksDataBase = listOf<Book>()
        //Используем backing field
        set(value) {
            //Если придет такое же значение то мы выходим из метода
            if (field == value) return
            //Если прило другое значение, то кладем его в переменную
            field = value
            //Обновляем RV адаптер
            BooksAdapter.addItems(field)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchView.setOnClickListener {
            binding.searchView.isIconified = false
        }

        //Подключаем слушателя изменений введенного текста в поиска
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            //Этот метод отрабатывает при нажатии кнопки "поиск" на софт клавиатуре
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            //Этот метод отрабатывает на каждое изменения текста
            override fun onQueryTextChange(newText: String): Boolean {
                //Если ввод пуст то вставляем в адаптер всю БД
                if (newText.isEmpty()) {
                    BooksAdapter.addItems(BooksDataBase)
                    return true
                }
                //Фильтруем список на поискк подходящих сочетаний
                val result = BooksDataBase.filter {
                    //Чтобы все работало правильно, нужно и запроси и имя фильма приводить к нижнему регистру
                    it.title.lowercase(Locale.getDefault())
                        .contains(newText.lowercase(Locale.getDefault()))
                }
                //Добавляем в адаптер
                BooksAdapter.addItems(result)
                return true
            }
        })

        //находим наш RV
        initRecyckler()
        //Кладем нашу БД в RV
        viewModel.BooksListLiveData.observe(viewLifecycleOwner, Observer<List<Book>> {
            BooksDataBase = it
        })
    }


    private fun initRecyckler() {
        binding.mainRecycler.apply {
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
    }


}





