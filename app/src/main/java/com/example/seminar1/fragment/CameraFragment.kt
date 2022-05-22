package com.example.seminar1.fragment

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.example.seminar1.databinding.FragmentCameraBinding

class CameraFragment : Fragment() {
    private var _binding : FragmentCameraBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentCameraBinding.inflate(layoutInflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAuthButtonClickListeners()
    }

    private fun initAuthButtonClickListeners(){
        binding.btnAdd.setOnClickListener {
            goGallery()
        }
    }

    private val activityLauncher: ActivityResultLauncher<Intent> =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK && it.data != null) {
                    var currentImageUri = it.data?.data
                    Glide.with(requireActivity())
                            .load(currentImageUri)
                            .into(binding.ivPhoto)
                } else if (it.resultCode == RESULT_CANCELED) {
                    Toast.makeText(requireContext(), "사진 선택 취소", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "사진 첨부 실패", Toast.LENGTH_SHORT).show()
                }
            }

        private fun goGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        activityLauncher.launch(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}