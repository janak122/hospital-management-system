package org.raghuvir.hms.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.raghuvir.hms.beans.*;
import org.raghuvir.hms.daos.HibernateTemplet;
import org.raghuvir.hms.daos.ManageDoctorDAO;
import org.raghuvir.hms.daos.ManageDoctorDAOImpl;
import org.raghuvir.hms.daos.ManagePatientDAO;
import org.raghuvir.hms.daos.ManageRoomDAO;
import org.raghuvir.hms.daos.ManageStaffDAO;
import org.raghuvir.hms.dtos.RoomInfoDTO;
import org.raghuvir.hms.repositories.HmsUserRepository;
import org.raghuvir.hms.services.ManagePatientService;
import org.raghuvir.hms.services.ManagePatientServiceImpl;

public class Main {

	static String names[] = { "Judith","Judson","Judy","Juergen","Jule","Jules","Julian","Julie","Julio","Julius","Justin","Justis","Kaiser","Kaleb","Kalil","Kalle","Kalman","Kalvin","Kam","Kane","Kareem","Karel","Karim","Karl","Karsten","Kaspar","Keefe","Keenan","Keene","Keil","Keith","Kellen","Kelley","Kelly","Kelsey","Kelvin","Kelwin","Ken","Kendal","Kendall","Kendrick","Kenn","Kennedy","Kenneth","Kenny","Kent","Kenton","Kenyon","Kermie","Kermit","Kerry","Kevan","Kevin","Kim","Kimball","Kimmo","Kin","Kincaid","King","Kingsley","Kingsly","Kingston","Kip","Kirby","Kirk","Kit","Klaus","Klee","Knox","Konrad","Konstantin","Kory","Kostas","Kraig","Kris","Krishna","Kristian","Kristopher","Kristos","Kurt","Kurtis","Kyle","Laird","Lamar","Lambert","Lamont","Lance","Lancelot","Lane","Langston","Lanny","Larry","Lars","Laurance","Lauren","Laurence","Laurens","Laurent","Laurie","Lawerence","Lawrence","Lawson","Lawton","Lay","Layton","Lazar","Lazare","Lazaro","Lazarus","Lazlo","Lee","Lefty","Leif","Leigh","Leighton","Leland","Lem","Lemar","Lemmie","Lemmy","Lemuel","Len","Lenard","Lennie","Lenny","Leo","Leon","Leonard","Leonardo","Leonerd","Leonhard","Leonid","Leonidas","Leopold","Leroy","Les","Lesley","Leslie","Lester","Lev","Levi","Levin","Levon","Levy","Lew","Lewis","Lex","Liam","Lin","Lincoln","Lind","Lindsay","Lindsey","Lindy","Linoel","Linus","Lion","Lionel","Lionello","Llewellyn","Lloyd","Locke","Lockwood","Logan","Lon","Lonnie","Lonny","Loren","Lorenzo","Lorne","Lorrie","Lothar","Lou","Louie","Louis","Lovell","Lowell","Lucas","Luce","Lucian","Luciano","Lucien","Lucio","Lucius","Ludvig","Ludwig","Luigi","Luis","Lukas","Luke","Luther","Lyle","Lyn","Lyndon","Lynn","Mac","Mace","Mack","Mackenzie","Maddie","Maddy","Madison","Magnum","Magnus","Mahesh","Mahmoud","Mahmud","Maison","Major","Malcolm","Manfred","Manish","Manny","Manuel","Marc","Marcel","Marcello","Marcellus","Marcelo","Marchall","Marcio","Marco","Marcos","Marcus","Marietta","Marilu","Mario","Marion","Marius","Mark","Marko","Markos","Markus","Marlin","Marlo","Marlon","Marlow","Marlowe","Marmaduke","Marsh","Marshal","Marshall","Mart","Martainn","Marten","Martie","Martin","Martino","Marty","Martyn","Marv","Marve","Marven","Marvin","Marwin","Mason","Mateo","Mathew","Mathias","Matias","Matt","Matteo","Matthaeus","Mattheus","Matthew","Matthias","Matthieu","Matthiew","Matthus","Mattias","Mattie","Matty","Maurice","Mauricio","Maurie","Maurise","Maurits","Mauritz","Maury","Max","Maxfield","Maxie","Maxim","Maximilian","Maximilien","Maxwell","Mayer","Maynard","Maynord","Mayor","Mead","Meade","Meier","Meir","Mel","Melvin","Melvyn","Menard","Mendel","Mendie","Meredeth","Meredith","Merell","Merill","Merle","Merlin","Merrel","Merrick","Merril","Merrill","Merry","Merv","Mervin","Merwin","Meryl","Meyer","Mic","Micah","Michael","Michail","Michal","Michale","Micheal","Micheil","Michel","Michele","Mick","Mickey","Mickie","Micky","Miguel","Mika","Mikael","Mike","Mikel","Mikey","Mikhail","Miles","Millicent","Milo","Milt","Milton","Mischa","Mitch","Mitchael","Mitchel","Mitchell","Moe","Mohamad","Mohamed","Mohammad","Mohammed","Mohan","Moise","Moises","Moishe","Monroe","Montague","Monte","Montgomery","Monty","Moore","Mordecai","Morgan","Morlee","Morley","Morly","Morrie","Morris","Morry","Morse","Mort","Morten","Mortie","Mortimer","Morton","Morty","Mose","Moses","Moshe","Moss","Muffin","Mugsy","Muhammad","Munmro","Munroe","Murdoch","Murdock","Murphy","Murray","Mustafa","Myke","Myles","Mylo","Myron","Nahum","Napoleon","Nat","Natale","Nate","Nathan","Nathanael","Nathanial","Nathaniel","Nathanil","Neal","Neale","Neall","Nealon","Nealson","Nealy","Ned","Neddie","Neddy","Neel","Neil","Nels","Nelsen","Nelson","Nero","Neron","Nester","Nestor","Nev","Nevil","Nevile","Neville","Nevin","Nevins","Newton","Niall","Niccolo","Nicholas","Nichole","Nichols","Nick","Nickey","Nickie","Nickolas","Nicky","Nico","Nicolas","Niels","Nigel","Niki","Nikita","Nikki","Nikolai","Nikos","Niles","Nils","Nilson","Niven","Noach","Noah","Noam","Noble","Noe","Noel","Nolan","Noland","Norbert","Norm","Norman","Normand","Normie","Norris","Northrop","Northrup","Norton","Norwood","Nunzio","Obadiah","Obadias","Oberon","Obie","Octavius","Odell","Odie","Odin","Odysseus","Olaf","Olag","Ole","Oleg","Olin","Oliver","Olivier","Olle","Ollie","Omar","Oral","Oran","Orazio","Orbadiah","Oren","Orin","Orion","Orlando","Orren","Orrin","Orson","Orton","Orville","Osbert","Osborn","Osborne","Osbourn","Osbourne","Oscar","Osgood","Osmond","Osmund","Ossie","Oswald","Oswell","Otes","Othello","Otho","Otis","Otto","Owen","Ozzie","Ozzy","Pablo","Pace","Paco","Paddie","Paddy","Padraig","Page","Paige","Pail","Palmer","Paolo","Park","Parke","Parker","Parnell","Parrnell","Parry","Parsifal","Partha","Pascal","Pascale","Pasquale","Pat","Pate","Patel","Paten","Patin","Paton","Patric","Patrice","Patricio","Patrick","Patrik","Patsy","Pattie","Patty","Paul","Paulo","Pavel","Pearce","Pedro","Peirce","Pembroke","Pen","Penn","Pennie","Penny","Penrod","Pepe","Pepillo","Pepito","Perceval","Percival","Percy","Perry","Pete","Peter","Petey","Petr","Peyter","Peyton","Phil","Philbert","Philip","Phillip","Phillipe","Phillipp","Phineas","Phip","Pierce","Pierre","Pierson","Piet","Pieter","Pietro","Piggy","Pincas","Pinchas","Pincus","Piotr","Pip","Plato","Pooh","Porter","Poul","Powell","Praneetf","Prasad","Prasun","Prent","Prentice","Prentiss","Prescott","Preston","Price","Prince","Pryce","Puff","Purcell","Putnam","Pyotr","Quent","Quentin","Quiggly","Quigly","Quigman","Quill","Quillan","Quincey","Quincy","Quinlan","Quinn","Quint","Quintin","Quinton","Quintus","Rab","Rabbi","Rabi","Rad","Radcliffe","Rafael","Rafe","Ragnar","Raimund","Rainer","Raj","Rajeev","Raleigh","Ralf","Ralph","Ram","Ramesh","Ramon","Ramsay","Ramsey","Rand","Randal","Randall","Randell","Randi","Randie","Randolf","Randolph","Randy","Ransell","Ransom","Raoul","Raphael","Raul","Ravi","Ravil","Rawley","Ray","Raymond","Raymund","Raymundo","Raynard","Rayner","Raynor","Reagan","Red","Redford","Redmond","Reece","Reed","Rees","Reese","Reg","Regan","Regen","Reggie","Reggis","Reggy","Reginald","Reginauld","Reid","Reilly","Reinhard","Reinhold","Rem","Remington","Remus","Renado","Renaldo","Renard","Renato","Renaud","Renault","Rene","Reube","Reuben","Reuven","Rex","Rey","Reynard","Reynold","Reynolds","Reza","Rhett","Ric","Ricard","Ricardo","Riccardo","Rice","Rich","Richard","Richardo","Richie","Richmond","Richy","Rick","Rickard","Rickey","Ricki","Rickie","Ricky","Rik","Rikki","Riley","Rinaldo","Ripley","Ritch","Ritchie","Roarke","Rob","Robb","Robbert","Robbie","Robert","Roberto","Robin","Robinson","Rochester","Rock","Rockwell","Rocky","Rod","Rodd","Roddie","Roddy","Roderic","Roderich","Roderick","Roderigo","Rodge","Rodger","Rodney","Rodolfo","Rodolph","Rodolphe","Rodrick","Rodrigo","Rodrique","Rog","Roger","Rogers","Roice","Roland","Rolando","Rolf","Rolfe","Rolland","Rollin","Rollins","Rollo","Rolph","Romain","Roman","Romeo","Ron","Ronald","Ronen","Roni","Ronnie","Ronny","Roosevelt","Rory","Roscoe","Ross","Roth","Rourke","Rowland","Roy","Royal","Royce","Rube","Ruben","Rubin","Ruby","Rudd","Ruddie","Ruddy","Rudie","Rudiger","Rudolf","Rudolfo","Rudolph","Rudy","Rudyard","Rufe","Rufus","Rupert","Ruperto","Russ","Russel","Russell","Rustie","Rustin","Rusty","Rutger","Rutherford","Rutledge","Rutter","Ryan","Sal","Salem","Salim","Salman","Salmon","Salomo","Salomon","Salomone","Salvador","Salvatore","Salvidor","Sam","Sammie","Sammy","Sampson","Samson","Samuel","Samuele","Sancho","Sander","Sanders","Sanderson","Sandor","Sandro","Sandy","Sanford","Sanson","Sansone","Sarge","Sargent","Sascha","Sasha","Saul","Sauncho","Saunder","Saunders","Saunderson","Saundra","Saw","Sawyer","Sawyere","Sax","Saxe","Saxon","Say","Sayer","Sayers","Sayre","Sayres","Scarface","Schroeder","Schuyler","Scot","Scott","Scotti","Scottie","Scotty","Seamus","Sean","Sebastian","Sebastiano","Sebastien","See","Selby","Selig","Serge","Sergeant","Sergei","Sergent","Sergio","Seth","Seymour","Shadow","Shaine","Shalom","Shamus","Shanan","Shane","Shannan","Shannon","Shaughn","Shaun","Shaw","Shawn","Shay","Shayne","Shea","Sheff","Sheffie","Sheffield","Sheffy","Shelby","Shelden","Sheldon","Shell","Shelley","Shelton","Shem","Shep","Shepard","Shepherd","Sheppard","Shepperd","Sheridan","Sherlock","Sherlocke","Sherman","Sherwin","Sherwood","Sherwynd","Shimon","Shlomo","Sholom","Shorty","Shurlock","Shurlocke","Shurwood","Si","Sibyl","Sid","Siddhartha","Sidnee","Sidney","Siegfried","Siffre","Sig","Sigfrid","Sigfried","Sigmund","Silas","Silvain","Silvan","Silvano","Silvanus","Silvester","Silvio","Sim","Simeon","Simmonds","Simon","Simone","Sinclair","Sinclare","Sivert","Siward","Skell","Skelly","Skip","Skipp","Skipper","Skippie","Skippy","Skipton","Sky","Skye","Skylar","Skyler","Slade","Slim","Sloan","Sloane","Sly","Smith","Smitty","Socrates","Sol","Sollie","Solly","Solomon","Somerset","Son","Sonnie","Sonny","Sparky","Spence","Spencer","Spense","Spenser","Spike","Spiro","Spiros","Spud","Srinivas","Stacy","Staffard","Stafford","Staford","Stan","Standford","Stanfield","Stanford","Stanislaw","Stanleigh","Stanley","Stanly","Stanton","Stanwood","Stavros","Stearn","Stearne","Stefan","Stefano","Steffen","Stephan","Stephanus","Stephen","Sterling","Stern","Sterne","Steve","Steven","Stevie","Stevy","Stew","Steward","Stewart","Stig","Stillman","Stillmann","Sting","Stinky","Stirling","Stu","Stuart","Sturgis","Sullivan","Sully","Sumner","Sunny","Sutherland","Sutton","Sven","Swen","Syd","Sydney","Sylvan","Sylvester","Tab","Tabb","Tabbie","Tabby","Taber","Tabor","Tad","Tadd","Taddeo","Taddeus","Tadeas","Tailor","Tait","Taite","Talbert","Talbot","Tallie","Tally","Tam","Tamas","Tammie","Tammy","Tan","Tann","Tanner","Tanney","Tannie","Tanny","Tarrance","Tarrant","Tarzan","Tate","Taylor","Teador","Ted","Tedd","Teddie","Teddy","Tedie","Tedman","Tedmund","Tedrick","Temp","Temple","Templeton","Teodoor","Teodor","Teodorico","Teodoro","Terence","Terencio","Terrance","Terrel","Terrell","Terrence","Terri","Terrill","Terry","Thacher","Thad","Thaddeus","Thaddius","Thaddus","Thadeus","Thain","Thaine","Thane","Tharen","Thatch","Thatcher","Thaxter","Thayne","Thebault","Thedric","Thedrick","Theo","Theobald","Theodor","Theodore","Theodoric","Theophyllus","Thibaud","Thibaut","Thom","Thomas","Thor","Thorn","Thorndike","Thornie","Thornton","Thorny","Thorpe","Thorstein","Thorsten","Thorvald","Thurstan","Thurston","Tibold","Tiebold","Tiebout","Tiler","Tim","Timmie","Timmy","Timothee","Timotheus","Timothy","Tirrell","Tito","Titos","Titus","Tobe","Tobiah","Tobias","Tobie","Tobin","Tobit","Toby","Tod","Todd","Toddie","Toddy","Tom","Tomas","Tome","Tomkin","Tomlin","Tommie","Tommy","Tonnie","Tony","Tore","Torey","Torin","Torr","Torrance","Torre","Torrence","Torrey","Torrin","Torry","Town","Towney","Townie","Townsend","Towny","Trace","Tracey","Tracie","Tracy","Traver","Travers","Travis","Tray","Tre","Tremain","Tremaine","Tremayne","Trent","Trenton","Trev","Trevar","Trever","Trevor","Trey","Trip","Tristan","Troy","Truman","Tuck","Tucker","Tuckie","Tucky","Tudor","Tull","Tulley","Tully","Turner","Ty","Tybalt","Tye","Tyler","Tymon","Tymothy","Tynan","Tyrone","Tyrus","Tyson","Udale","Udall","Udell","Ugo","Ulberto","Uli","Ulick","Ulises","Ulric","Ulrich","Ulrick","Ulysses","Umberto","Upton","Urbain","Urban","Urbano","Urbanus","Uri","Uriah","Uriel","Urson","Vachel","Vaclav","Vail","Val","Valdemar","Vale","Valentin","Valentine","Van","Vance","Vasili","Vasilis","Vasily","Vassili","Vassily","Vaughan","Vaughn","Venkat","Verge","Vergil","Vern","Verne","Vernen","Verney","Vernon","Vernor","Vic","Vick","Victor","Vijay","Vilhelm","Vin","Vince","Vincent","Vincents","Vinnie","Vinny","Vinod","Virge","Virgie","Virgil","Virgilio","Vite","Vito","Vlad","Vladamir","Vladimir","Voltaire","Von","Wade","Wadsworth","Wain","Waine","Wainwright","Wait","Waite","Waiter","Wake","Wakefield","Wald","Waldemar","Walden","Waldo","Waldon","Waleed","Walker","Wallace","Wallache","Wallas","Wallie","Wallis","Wally","Walsh","Walt","Walter","Walther","Walton","Wang","Ward","Warde","Warden","Ware","Waring","Warner","Warren","Wash","Washington","Wat","Waverley","Waverly","Way","Waylan","Wayland","Waylen","Waylin","Waylon","Wayne","Web","Webb","Weber","Webster","Weidar","Weider","Welbie","Welby","Welch","Wells","Welsh","Wendall","Wendel","Wendell","Werner","Wes","Wesley","Weslie","West","Westbrook","Westbrooke","Westleigh","Westley","Weston","Weylin","Wheeler","Whit","Whitaker","Whitby","Whitman","Whitney","Whittaker","Wiatt","Wilber","Wilbert","Wilbur","Wilburn","Wilburt","Wilden","Wildon","Wilek","Wiley","Wilfred","Wilfrid","Wilhelm","Will","Willard","Willdon","Willem","Willey","Willi","William","Willie","Willis","Willmott","Willy","Wilmar","Wilmer","Wilson","Wilt","Wilton","Win","Windham","Winfield","Winford","Winfred","Winifield","Winn","Winnie","Winny","Winslow","Winston","Winthrop","Winton","Wit","Witold","Wittie","Witty","Wojciech","Wolf","Wolfgang","Wolfie","Wolfram","Wolfy","Woochang","Wood","Woodie","Woodman","Woodrow","Woody","Worden","Worth","Worthington","Worthy","Wright","Wyatan","Wyatt","Wye","Wylie","Wyn","Wyndham","Wynn","Wynton","Xavier","Xenos","Xerxes","Xever","Ximenes","Ximenez","Xymenes","Yaakov","Yacov","Yale","Yanaton","Yance","Yancey","Yancy","Yank","Yankee","Yard","Yardley","Yehudi","Yigal","Yule","Yuri","Yves","Zach","Zacharia","Zachariah","Zacharias","Zacharie","Zachary","Zacherie","Zachery","Zack","Zackariah","Zak","Zalman","Zane","Zared","Zary","Zeb","Zebadiah","Zebedee","Zebulen","Zebulon","Zechariah","Zed","Zedekiah","Zeke","Zelig","Zerk","Zeus","Zippy","Zollie","Zolly","Zorro","Rahul","Shumeet","Vibhu"};

	public static void main(String args[]) throws ParseException {
		
		IDGenerator idg=IDGenerator.getInstance();		
		try {
			HibernateTemplet.executeTemplate(NewHibernateUtil.getSessionFactory(), (Session session) -> {
				OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
				int year = 15;
				long num = (now.getDayOfYear() - 1) * 60 * ((60 * 23) + 56);
				num += (now.getHour() * 60 * 60) + (now.getMinute() * 60) + (now.getSecond());
				SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
				Date date;
				long ph=9890596869l;
				try {
					date = format.parse("1995/12/02");							
				for(String nm:names) {
					num++;ph++;
					Calendar cal=Calendar.getInstance();
					cal.setTime(date);cal.add(Calendar.DAY_OF_MONTH,1);
					date=cal.getTime();
					session.save(new PatientBEAN(new HashSet<>(),
							new LinkedList<>(), 
							"P19"+idg.convertBase36(num), nm, "default.png",
							""+ph, EntitiesConstants.PATIENT, 
							"home", "female",nm+"@gmail.com", date));
					}
				} catch (Exception e) {
				}
				return null;
			});
		} finally {
			NewHibernateUtil.closeSessionFactory();
		}
	}

	public static Date getDate(String date) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		return format.parse(date);
	}

	public static void modifyDates() {
		HibernateTemplet.executeTemplate(NewHibernateUtil.getSessionFactory(), (Session session) -> {
			StaffBEAN obj = (StaffBEAN) session.get(StaffBEAN.class, "S2046M5J");
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			try {
				obj.setArrivaltime(format.parse("02-12-2020 14:30:45"));
			} catch (ParseException ex) {
			}
			session.saveOrUpdate(obj);
			System.out.println(obj);
			return null;
		});
		HibernateTemplet.executeTemplate(NewHibernateUtil.getSessionFactory(), (Session session) -> {
			StaffBEAN obj = (StaffBEAN) session.get(StaffBEAN.class, "S2046M5J");
			System.out.println(obj.getArrivaltime().getHours());
			System.out.println(obj.getArrivaltime().getMinutes());
			System.out.println(obj.getArrivaltime().getSeconds());

			System.out.println(obj);
			return null;
		});
	}

	public static void addRecords() {
		IDGenerator idg = IDGenerator.getInstance();
		String[] patients = { "rahul", "keshav", "arjun", "smit", "amit", "nand", "suresh" };
		String[] doctors = { "tiwari", "chavada", "mahesh", "ramesh" };
		String[] types = { "dental", "mental", "homiophethic", "allinone" };
		String[] degrees = { "mbbs;md", "phd", "ms", "mbbs;phd;ms;md" };
		String[] staffs = { "menka", "sakuntala", "parita", "laila", "juliat" };

		HibernateTemplet.executeTemplate(NewHibernateUtil.getSessionFactory(), (Session session) -> {
			int i = 0;
			for (String name : patients) {
				session.saveOrUpdate(new PatientBEAN(new HashSet(), new LinkedList(), idg.getNextId("P"), name,
						"/default.png", "942915195" + i, EntitiesConstants.PATIENT, "home", "male", name + "@gmail.com",
						new Date(2000, 12, 2)));
				i++;
				System.out.println(i);
			}
			i = 0;
			for (String name : doctors) {
				session.saveOrUpdate(new DoctorBEAN(degrees[i], types[i], new HashSet<>(), 1000000, new Date(),
						new Date(0, 0, 0, 8, 0, 0), new Date(0, 0, 0, 18, 0, 0), "doctor", idg.getNextId("D"), name,
						"/default.png", "842915175" + i, EntitiesConstants.DOCTOR, "home", "male", name + "@gmail.com",
						new Date(2000, 12, 2)));
				i++;
				System.out.println(i);

			}
			i = 0;
			for (String name : staffs) {
				session.saveOrUpdate(
						new StaffBEAN(1000000, new Date(), new Date(0, 0, 0, 8, 0, 0), new Date(0, 0, 0, 18, 0, 0),
								"receptionist", idg.getNextId("S"), name, "/default.png", "742915195" + i,
								EntitiesConstants.STAFF, "home", "female", name + "@gmail.com", new Date(2000, 12, 2)));
				i++;
				System.out.println(i);

			}
			return null;
		});
	}

	public static void insertRooms() {
		HibernateTemplet.executeTemplate(NewHibernateUtil.getSessionFactory(), (Session session) -> {
			for (int i = 5; i < 10; i++) {
				session.saveOrUpdate(new RoomBEAN("RR10" + i, "regular", new LinkedList()));
				session.saveOrUpdate(new RoomBEAN("RG10" + i, "general", new LinkedList()));
				session.saveOrUpdate(new RoomBEAN("RV10" + i, "vip", new LinkedList()));
			}
			return null;
		});
	}

}
