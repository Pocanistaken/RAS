package com.ras.drawer;

import com.github.weisj.jsvg.nodes.Title;
import com.ras.entity.account.Employee;
import com.ras.form.AccountForm;
import com.ras.form.CategoryForm;
import com.ras.form.DashboardForm;
import com.ras.form.MenuForm;
import com.ras.form.ProductForm;
import com.ras.form.RegionForm;
import com.ras.form.RegionManagerForm;
import com.ras.form.TableForm;
import com.ras.form.TableManagerForm;
import raven.drawer.component.SimpleDrawerBuilder;
import raven.drawer.component.footer.SimpleFooterData;
import raven.drawer.component.header.SimpleHeaderData;
import raven.drawer.component.menu.MenuAction;
import raven.drawer.component.menu.MenuEvent;
import raven.drawer.component.menu.MenuValidation;
import raven.drawer.component.menu.SimpleMenuOption;
import com.ras.manager.FileManager;
import raven.swing.AvatarIcon;
import com.ras.tabbed.WindowsTabbed;

public class MyDrawerBuilder extends SimpleDrawerBuilder {
    
    @Override
    public SimpleHeaderData getSimpleHeaderData() {
        return new SimpleHeaderData()
                .setIcon(new AvatarIcon(FileManager.getInstance().getAvatarFile().getAbsolutePath(), 60, 60, 999))
                .setTitle(Employee.getInstance().getName() + " " + Employee.getInstance().getSurname())
                .setDescription(Employee.getInstance().getRole());
                }
                

    @Override
    public SimpleMenuOption getSimpleMenuOption() {
        String menus[][] = {
            {"~MAIN~"},
            {"Dashboard"},
            {"~RESTAURANT MANAGMENT~"},
            {"Table", "View", "Edit"},
            {"Menu", "View", "Edit"},
            {"Region", "View", "Edit"},
            {"Category", "View", "Edit"},
            {"Product", "View", "Edit"},
            {"Inventory", "View", "Suggestions"},
            {"~REPORTS~"},
            {"Statistic", "View", "Suggestions"},
            {"~OTHER~"},
            {"Profile", "View", "Edit"},
            {"Logout"}};

        String icons[] = {
            "dashboard.svg",
            "email.svg",
            "chat.svg",
            "calendar.svg",
            "ui.svg",
            "forms.svg",
            "chart.svg",
            "icon.svg",
            "page.svg",
            "logout.svg"};

        return new SimpleMenuOption()
                .setMenus(menus)
                .setIcons(icons)
                .setBaseIconPath("com/ras/drawer/icon")
                .setIconScale(0.45f)
                .addMenuEvent(new MenuEvent() {
                    @Override
                    public void selected(MenuAction action, int index, int subIndex) {
                        if (index == 0) {
                            WindowsTabbed.getInstance().addTab("Dashboard", new DashboardForm());
                        }
                        
                        if (index == 1) {
                            
                            if (subIndex == 1) {
                                WindowsTabbed.getInstance().addTab("Table Manager", new RegionManagerForm());
                                          
                            }
                            
                            if (subIndex == 2) {
                                
                                WindowsTabbed.getInstance().addTab("Table Edit", new TableForm());
                                          
                            }
                            
                        }
                        
                        if (index == 2) {
                            
                            if (subIndex == 1) {
                                
                                WindowsTabbed.getInstance().addTab("Menu Edit", new MenuForm());
                        
                                
                            }
                            
                        }
                        
                        if (index == 3) {
                            
                            if (subIndex == 1) {
                                
                                WindowsTabbed.getInstance().addTab("Region Edit", new RegionForm());
                                          
                            }
                            
                        }
                        
                        if (index == 4) {
                            
                            if (subIndex == 1) {
                                
                                WindowsTabbed.getInstance().addTab("Category Edit", new CategoryForm());

                                
                            }
                            
                        }
                        
                        
                        if (index == 5) {
                            
                            if (subIndex == 1) {
                                
                                WindowsTabbed.getInstance().addTab("Product Edit", new ProductForm());

                                
                            }
                            
                        }
                        
                        if (index == 8) {
                            
                            if (subIndex == 1) { // Profile View
                                WindowsTabbed.getInstance().addTab("Account View", new AccountForm());
                                
                            }
                            if (subIndex == 2) { // Profile Edit
                                WindowsTabbed.getInstance().addTab("Account Edit", new AccountForm());

                            }
                            
                        }
                        
                        
                        
                        System.out.println("Menu selected " + index + " " + subIndex);
                    }
                })
                .setMenuValidation(new MenuValidation() {
                    @Override
                    public boolean menuValidation(int index, int subIndex) {
                        
//                        if(index==0){
//                            return false;
//                        }else if(index==3){
//                            return false;
//                        }



                        return true;
                    }

                });
    }

    @Override
    public SimpleFooterData getSimpleFooterData() {
        return new SimpleFooterData()
                .setTitle("Restaurant Automation System ")
                .setDescription("Version 1.0.0");
    }

    @Override
    public int getDrawerWidth() {
        return 275;
    }
}
