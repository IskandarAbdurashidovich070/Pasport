package com.example.pasport

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.example.pasport.databinding.FragmentAddBinding
import com.example.pasport.db.AppDatabase
import com.example.pasport.db.User
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private lateinit var appDatabase: AppDatabase
    private lateinit var list: ArrayList<User>
    private lateinit var user: User
    private var path = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(layoutInflater)

        user = User()

        list = ArrayList()

        appDatabase = AppDatabase.getInstance(binding.root.context)

        binding.image.setOnClickListener {
            val title = SimpleDateFormat("dd.MM.yyyy_hh.mm.ss").format(Date())
            setImage.launch("image/*")
        }


        var listSeriya = ArrayList<String>()


        binding.btnAdd.setOnClickListener {
            val user = User(
                binding.name.text.toString(),
                binding.surname.text.toString(),
                SeriyaBer(listSeriya),
                path
                )
            Data.name = user.number.toString()
            appDatabase.userDao().addPassport(user)
            list.add(user)
            findNavController().navigate(R.id.homeFragment)
        }

        binding.passportNum.text = Data.name




        return binding.root
    }


    private var setImage =
        registerForActivityResult(ActivityResultContracts.GetContent()){
            it ?: return@registerForActivityResult
            binding.image.setImageURI(it)
            val inputStream = requireActivity().contentResolver?.openInputStream(it)
            val title = SimpleDateFormat("dd.MM.yyyy_hh.mm.ss").format(Date())
            val file = File(requireActivity().filesDir, "${title}.jpg")
            val outputStream = FileOutputStream(file)
            inputStream?.copyTo(outputStream)
            inputStream?.close()
            outputStream.close()
            path = file.absolutePath
            Data.path = path
        }


    fun SeriyaBer(listSeriya: List<String>): String {
        var seriya = ""
        var n1 = Random().nextInt(25)
        var n2 = Random().nextInt(25)
        var q = 0
        for (i in 'A'..'Z') {
            if (q == n1) {
                seriya += i
            }
            if (q == n2) {
                seriya += i

            }
            q++
        }
        for (i in 0..6) {
            seriya += Random().nextInt(10)
        }
        for (s in listSeriya) {
            if (s == seriya)
                SeriyaBer(listSeriya)
            break
        }
        return seriya
    }

}