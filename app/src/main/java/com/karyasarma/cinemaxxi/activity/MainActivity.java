package com.karyasarma.cinemaxxi.activity;

import android.graphics.Color;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.karyasarma.cinemaxxi.R;
import com.karyasarma.cinemaxxi.activity.fragment.PrimaryFragment;
import com.karyasarma.cinemaxxi.activity.fragment.ReminderFragment;
import com.karyasarma.cinemaxxi.activity.fragment.SecondaryFragment;
import com.karyasarma.cinemaxxi.activity.fragment.SimpleFragmentListener;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

/**
 *
 * @author Daniel Joi Partogi Hutapea
 */
public class MainActivity extends AppCompatActivity implements AccountHeader.OnAccountHeaderListener, Drawer.OnDrawerItemClickListener, SimpleFragmentListener
{
    private static final int REMINDER_MENU = 1;
    private static final int PRIMARY_MENU = 2;
    private static final int SECONDARY_MENU = 3;
    private Drawer drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initDrawerMenu();
        initDefaultFragment();
    }

    public void initDrawerMenu()
    {
        ProfileDrawerItem profileDrawerItem = new ProfileDrawerItem()
                .withName("Daniel Joi Partogi Hutapea")
                .withEmail("daniel.hutapea@gmail.com")
                .withIcon(getDrawable(R.drawable.profile));

        AccountHeader accountHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.account_header_bg)
                .addProfiles(profileDrawerItem)
                .withOnAccountHeaderListener(this)
                .build();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        PrimaryDrawerItem item1 = new PrimaryDrawerItem()
                .withIdentifier(REMINDER_MENU)
                .withName("Reminder")
                .withIcon(GoogleMaterial.Icon.gmd_home)
                .withBadge("10")
                .withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).withColorRes(R.color.md_red_700));

        SecondaryDrawerItem item2 = (SecondaryDrawerItem) new SecondaryDrawerItem()
                .withIdentifier(PRIMARY_MENU)
                .withName("Settings")
                .withIcon(GoogleMaterial.Icon.gmd_settings);

        SecondaryDrawerItem item3 = (SecondaryDrawerItem) new SecondaryDrawerItem()
                .withIdentifier(SECONDARY_MENU)
                .withName("Exit")
                .withIcon(GoogleMaterial.Icon.gmd_exit_to_app);

        drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(accountHeader)
                .addDrawerItems(item1, new DividerDrawerItem(), item2, item3)
                .addStickyDrawerItems(new PrimaryDrawerItem().withName("Powered by Karyasarma"))
                .withOnDrawerItemClickListener(this)
                .build();
    }

    public void initDefaultFragment()
    {
        setSelectedFragment(REMINDER_MENU);
    }

    /**
     *
     * @param view
     * @param profile
     * @param current
     * @return
     */
    @Override
    public boolean onProfileChanged(View view, IProfile profile, boolean current)
    {
        return false;
    }

    /**
     *
     * @param view
     * @param position
     * @param drawerItem
     * @return false - Always return false to make the drawer auto close after item clicked.
     */
    @Override
    public boolean onItemClick(View view, int position, IDrawerItem drawerItem)
    {
        setSelectedFragment((int)drawerItem.getIdentifier());
        return false;
    }

    private void setSelectedFragment(long identifier)
    {
        if(drawer.getCurrentSelection()!=identifier)
        {
            drawer.setSelection(identifier);
        }

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        switch((int)identifier)
        {
            case REMINDER_MENU: fragmentTransaction.replace(R.id.fragment_container, ReminderFragment.newInstance(), ReminderFragment.TAG); break;
            case PRIMARY_MENU: fragmentTransaction.replace(R.id.fragment_container, PrimaryFragment.newInstance(), PrimaryFragment.TAG); break;
            case SECONDARY_MENU: fragmentTransaction.replace(R.id.fragment_container, SecondaryFragment.newInstance(), SecondaryFragment.TAG); break;
        }

        fragmentTransaction.commit();
    }
}
