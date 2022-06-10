package com.example.seminar1.ui.home.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.bumptech.glide.Glide
import com.example.seminar1.R
import com.example.seminar1.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private var position = FOLLOWER_POSITION
    private var _binding : FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentProfileBinding.inflate(layoutInflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initTransactionEvent()
        initImage()
    }

    private fun initTransactionEvent(){

        childFragmentManager.commit{
            setReorderingAllowed(true)
            add<FollowerFragment>(R.id.fragment_list)
        }

        with(binding) {
            btnFollower.isSelected = true

            btnFollower.setOnClickListener {
                btnFollower.isSelected = true
                btnRepo.isSelected = false
                if (position == REPO_POSITION) {
                    replaceFragment<FollowerFragment>(FOLLOWER_POSITION)
                }
            }
            btnRepo.setOnClickListener {
                binding.btnRepo.isSelected = true
                binding.btnFollower.isSelected = false
                if (position == FOLLOWER_POSITION){
                    replaceFragment<RepositoryFragment>(REPO_POSITION)
                }
            }
            ibSettings.setOnClickListener {
                val intent = Intent(context, SettingActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private inline fun <reified T: Fragment>replaceFragment(pos : Int){
        childFragmentManager.commit{
            setReorderingAllowed(true)
            replace<T>(R.id.fragment_list)
        }
        position = pos
    }

    private fun initImage(){
        Glide.with(this)
                .load(R.drawable.profile3)
                .circleCrop()
                .into(binding.ivProfile)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val FOLLOWER_POSITION = 1
        private const val REPO_POSITION = 2
    }
}