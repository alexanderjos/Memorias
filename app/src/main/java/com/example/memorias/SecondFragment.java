package com.example.memorias;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.os.Handler; // Asegúrate de importar Handler

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.memorias.databinding.FragmentSecondBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    int tags;
    int numero1 = 0, numero2 = 0;
    Button button1, button2;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Generar valores aleatorios y asignar a botones
        List<Integer> randomValues = generateRandomValues();
        assignValuesToButtons(randomValues);

        String nombre = SecondFragmentArgs.fromBundle(getArguments()).getNombre();
        String dificultad = SecondFragmentArgs.fromBundle(getArguments()).getDificultad();
        binding.txtnombre.setText("Hola " + nombre);

        // Asignar el click listener a todos los botones
        binding.btn1.setOnClickListener(v -> Memoria(binding.btn1));
        binding.btn2.setOnClickListener(v -> Memoria(binding.btn2));
        binding.btn3.setOnClickListener(v -> Memoria(binding.btn3));
        binding.btn4.setOnClickListener(v -> Memoria(binding.btn4));
        binding.btn5.setOnClickListener(v -> Memoria(binding.btn5));
        binding.btn6.setOnClickListener(v -> Memoria(binding.btn6));
        binding.btn7.setOnClickListener(v -> Memoria(binding.btn7));
        binding.btn8.setOnClickListener(v -> Memoria(binding.btn8));
        binding.btn9.setOnClickListener(v -> Memoria(binding.btn9));
        binding.btn10.setOnClickListener(v -> Memoria(binding.btn10));
        binding.btn11.setOnClickListener(v -> Memoria(binding.btn11));
        binding.btn12.setOnClickListener(v -> Memoria(binding.btn12));

        binding.btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SecondFragmentDirections.ActionSecondFragmentToFirstFragment action =
                        SecondFragmentDirections.actionSecondFragmentToFirstFragment(dificultad, nombre, tags);
                NavHostFragment.findNavController(SecondFragment.this).navigate(action);
            }
        });
    }

    private List<Integer> generateRandomValues() {
        List<Integer> values = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            values.add(i);
            values.add(i); // Añadir el número dos veces
        }
        Collections.shuffle(values); // Mezclar los valores aleatorios
        return values;
    }

    private void Memoria(Button button) {
        // Obtener el valor almacenado en el tag
        Integer value = (Integer) button.getTag();
        button.setText(String.valueOf(value));

        if (numero1 == 0) {
            numero1 = value;
            button1 = button;
            tags++;
        } else {
            numero2 = value;
            button2 = button;
            tags++;
            if (numero2 == numero1) {
                Toast.makeText(getActivity(), "Correcto", Toast.LENGTH_SHORT).show();
                // Deshabilitar los botones cuando se hace una coincidencia correcta
                button1.setEnabled(false);
                button2.setEnabled(false);
                numero1 = 0;
                numero2 = 0;

                // Verificar si todos los botones están deshabilitados
                verifcarBotones();
            } else {
                Toast.makeText(getActivity(), "Incorrecto", Toast.LENGTH_SHORT).show();
                // Usar Handler para retrasar la acción
                new Handler().postDelayed(() -> {
                    button1.setText("");
                    button2.setText("");
                }, 1000); // 1000 milisegundos = 1 segundo
                numero1 = 0;
                numero2 = 0;
            }
        }
    }

    private void verifcarBotones() {
        // Verificar si todos los botones están deshabilitados
        Button[] buttons = {
                binding.btn1, binding.btn2, binding.btn3,
                binding.btn4, binding.btn5, binding.btn6,
                binding.btn7, binding.btn8, binding.btn9,
                binding.btn10, binding.btn11, binding.btn12
        };

        boolean todosDeshabilitados = true; // Inicializar como verdadero

        for (Button btn : buttons) {
            if (btn.isEnabled()) {
                todosDeshabilitados = false; // Si algún botón está habilitado, cambiar a falso
                break;
            }
        }

        // Cambiar la visibilidad del botón volver si todos los botones están deshabilitados
        if (todosDeshabilitados) {
            binding.btnVolver.setVisibility(View.VISIBLE); // Mostrar el botón volver
        }
    }

    private void assignValuesToButtons(List<Integer> values) {
        // Asignar los valores generados a los botones
        Button[] buttons = {
                binding.btn1, binding.btn2, binding.btn3,
                binding.btn4, binding.btn5, binding.btn6,
                binding.btn7, binding.btn8, binding.btn9,
                binding.btn10, binding.btn11, binding.btn12
        };

        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setTag(values.get(i)); // Guardar el valor en el tag
            buttons[i].setText(""); // No mostrar el valor
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
