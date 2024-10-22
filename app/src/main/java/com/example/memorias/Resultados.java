package com.example.memorias;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.memorias.databinding.FragmentDificilBinding;
import com.example.memorias.databinding.FragmentResultadosBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Resultados#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Resultados extends Fragment {
    private FragmentResultadosBinding binding;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Resultados() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Resultados.
     */
    // TODO: Rename and change types and number of parameters
    public static Resultados newInstance(String param1, String param2) {
        Resultados fragment = new Resultados();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentResultadosBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String nombre = ResultadosArgs.fromBundle(getArguments()).getNombre();
        String dificultad = ResultadosArgs.fromBundle(getArguments()).getDificultad();
        int tags = ResultadosArgs.fromBundle(getArguments()).getTags();
        binding.txtBienvenida.setText("Hola "+nombre + ", Su dificultad fue : "+dificultad);
        double eficiencia = (dificultad.equals("Facil") ? (12.0 / tags) : (20.0 / tags)) ;

        binding.txtTags.setText("Has realizado "+tags +" tags");
        binding.txtEficiencia.setText(String.format("Eficiencia: %.2f", eficiencia*100)+"%");

    }
}