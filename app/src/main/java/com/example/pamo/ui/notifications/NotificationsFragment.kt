package com.example.pamo.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pamo.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {
    private var recipe1TextView: TextView? = null
    private var recipe2TextView: TextView? = null
    private var recipe3TextView: TextView? = null
    private val recipe1 =
        "  Mix the meats: Use a combination of ground beef and Italian pork sausage for the best flavor.Include chopped mushrooms: Mushrooms release moisture while they cook, so not only does including mushrooms in your meatball mixture add flavor, it helps keep the meatballs from drying out.Use a light hand: Don't over-mix the ingredients or the meatballs will be tough. Work the ingredients just enough so that the mixture comes together.Cook in batches: Rather than crowd the pan, cook the meatballs in batches in a single layer.\n"
    private val recipe2 =
        " How to Make Lamb Meatballs. These lamb meatballs are seasoned with orange zest, mint, cilantro, cumin, coriander, cinnamon, cayenne, and garlic.Orange zest isn’t especially common in kefta but is a popular fruit in Morocco. When I was there 20 years ago, the freshly squeezed orange juice in the markets just about saved our lives in the heat. In this dish, it boosts the flavor of the meatballs and balances the rich lamb meat.To keep the meatballs moist and to help it bind together, I add in an egg and panade, which is just breadcrumbs soaked in milk. Once shaped, the meatballs are broiled until lightly golden and glazed with pomegranate molasses. Along with the orange zest, the molasses here gives the final dish a sweet and sour tang that cuts through the richness of the lamb. "
    private val recipe3 = "Prepare the lemons" +
            "Preheat the oven to 350°F" +
            "Make the crust" +
            "Make the filling" +
            "Bake the lemon bars" +
            "Serve"
    private var binding: FragmentNotificationsBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val notificationsViewModel: NotificationsViewModel =
            ViewModelProvider(this).get<NotificationsViewModel>(
                NotificationsViewModel::class.java
            )
        binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        recipe1TextView = binding!!.textRecipe1
        recipe2TextView = binding!!.textRecipe2
        recipe3TextView = binding!!.textRecipe3
        val root: View = binding!!.getRoot()
        recipe1TextView!!.setText(recipe1)
        recipe2TextView!!.setText(recipe2)
        recipe3TextView!!.setText(recipe3)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}