package com.skiing;

import com.skiing.models.entities.Skiing;
import com.skiing.models.entities.User;
import com.skiing.models.enums.UserRole;
import com.skiing.services.SkiingService;
import com.skiing.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Random;


@Component
@AllArgsConstructor
public class StartUp implements CommandLineRunner {
    private final UserService userService;
    private final SkiingService skiingService;

    @Override
    public void run(String... args) {
        makeUsers();
        makeSkiing();
    }

    private void makeUsers() {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("Mihajlo", "Kotsubinsky", "mihajlo.kotsubinsky@gmail.com", "+380000000000", "password"));
        users.add(new User("Sofia", "Pokach", "sofia.pokach@gmail.com", "+380000000001", "password"));
        users.add(new User("Dmitriy", "Visneveckiy", "dmitriy.visneveckiy@gmail.com", "+380000000002", "password", UserRole.MANAGER));
        users.add(new User("Taras", "Shevchednko", "taras.shevchednko@gmail.com", "+380000000003", "password", UserRole.COURIER));
        userService.save(users);
    }

    private void makeSkiing() {
        Random random = new Random();

        ArrayList<Skiing> products = new ArrayList<>();
        products.add(new Skiing("Лижі OGSO Spencer 110 21-22",
                "https://boomerang-boardshop.ua/wp-content/uploads/2022/01/spencer-top-sheetFichier-1.jpg",
                "SPENCER – лижі середньої ваги для фрітуру, спроектовані так, щоб однаково добре пливти по пухляку у великих горах і легко нарізати невеликі дуги на улюбленому гірськолижному курорті. Якщо ви шукаєте міцні універсальні лижі, то SPENCER – саме те, що треба!",
                179, 12_288, random.nextInt(6)));
        products.add(new Skiing("Лижі OGSO Gervasutti 90 21-22",
                "https://boomerang-boardshop.ua/wp-content/uploads/2022/01/gervasutti-top-sheetFichier-1.jpg",
                "GERVASUTTI – універсальні лижі середньої ваги, які весело розсікатимуть беккантрі чи будуть нарізати кола на вашому улюбленому курорті. На них надзвичайно легко кататися! Завдяки правильній геометрії ви отримаєте масу задоволення на гірськолижній трасі і відчуєте найкраще в беккантрі.",
                176, 12_288, random.nextInt(6)));
        products.add(new Skiing("Лижі OGSO Diable 100 21-22",
                "https://boomerang-boardshop.ua/wp-content/uploads/2022/01/diable-top-sheetFichier-1-1.jpg",
                "DIABLE – лижі середньої ваги для фрітуру, які весело розсікатимуть беккантрі чи будуть нарізати кола на вашому улюбленому курорті. Отримали нагороду  Gear Guide 2022 від журналу Backcountry.",
                186, 12_288, random.nextInt(6)));
        products.add(new Skiing("Лижі OGSO Cosmique 90 21-22",
                "https://boomerang-boardshop.ua/wp-content/uploads/2022/01/cosmique-topsheetFichier-4.jpg",
                "COSMIQUE – одні з найпопулярніших лиж OGSO, універсальні, суперлегкі лижі для скітурів та просто веселого катання на пересіченій місцевості. Якщо ви шукаєте лижі з чудовою плавучістю, та можливістю легко долати викручуватися в різних умовах, то COSMIQUE саме для вас!",
                184, 14_336, random.nextInt(6)));
        products.add(new Skiing("Лижі Elan Porsche Design Elan Ace 21-22",
                "https://boomerang-boardshop.ua/wp-content/uploads/2022/01/elan-porsche-design-elan-ace-fusion-x-aajhnv21.jpg",
                "Elan співпрацює з Porsche Design вже три сезони. Porsche Design Elan Ace – це ідеальне поєднання визначного дизайну з високою продуктивністю на снігу. Це гібридні лижі, створені з використанням найновіших технологій і високоякісних матеріалів. Porsche Design Elan Ace поєднує в собі здатність ідеально виконувати короткі слаломні повороти або довгі швидкі повороти без необхідності вибору між ними. Створені для вимогливих лижників, які прагнуть досконалості карвінгу та універсальності, щоб зробити будь-який поворот. Комбінація технології Ace Arrow, агресивного кемберу, нового сердечника R² FRAME , RST боковин та подвійного титанового шару виведе ваше катання на новий рівень. Карбоновий зміцнюючий шар надає додаткову потужність лижам. Вони перевершать ваші очікування, адже тут зустрічаються два легендарні бренди в обдасті інновацій і дизайну.\n" +
                        "\n" +
                        "Укомплектовані кріпленнями EMX 12.0 GW Fusion X.\n",
                167, 45_184, random.nextInt(6)));
        products.add(new Skiing("Лижі Elan Porsche Design Elan Amphibio 21-22",
                "https://boomerang-boardshop.ua/wp-content/uploads/2022/01/elan-porsche-design-amphibio-fusion-x-abbhnp21.jpg",
                "Elan співпрацює з Porsche Design вже три сезони. Porsche Design Elan Amphibio – це ідеальне поєднання визначного дизайну з високою продуктивністю на снігу, ще більш універсальні завдяки ширшій основі. Ця елегантна машина, створена з використанням найновіших технологій і високоякісних матеріалів, понесе вас по будь-якому схилу! Porsche Design Elan Amphibio спроектовані таким чином, щоб бути більш стабільним, ніж гоночні лижі, і легими в управлінні , ніж лижі для backside. Ви отримаєте найміцніший захват краю, швидке входження та вихід з поворотів, плавну їзду, стабільність на великій швидкості. Вони перевершать ваші очікування, адже тут зустрічаються два легендарні бренди в обдасті інновацій і дизайну.\n" +
                        "\n" +
                        "Укомплектовані кріпленнями EMX 12.0 GW Fusion X.\n",
                166, 38_464, random.nextInt(6)));
        products.add(new Skiing("Лижі Elan Starr Qs El 4.5 (90 см) 21-22",
                "https://boomerang-boardshop.ua/wp-content/uploads/2022/01/elan-starr-quick-shift-afdgbh20-2d.jpg",
                "Юніори-новачки полюблять ці гірські лижі за легкість в управлінні, стабільність і віддачу на схилі. Конструкція U-Flex, сердечник Synflex Core, конструкція Full Power Cap і система Quick Shift полегшують проходження карвінгових поворотів навіть на мінімальних швидкостях. Всі ці якості роблять ці лижі ідеальним інструментом для навчання юніорів карвінговій техніці. Укомплектовані кріпленнями EL 4.5 GW Shift.",
                90, 4_704, random.nextInt(6)));
        skiingService.save(products);
    }
}
