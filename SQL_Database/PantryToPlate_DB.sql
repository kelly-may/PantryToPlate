USE [master]
GO
/****** Object:  Database [PantryToPlate]    Script Date: 8/15/2020 8:24:11 PM ******/
CREATE DATABASE [PantryToPlate]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'PantryToPlate', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\PantryToPlate.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'PantryToPlate_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\PantryToPlate_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [PantryToPlate] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [PantryToPlate].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [PantryToPlate] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [PantryToPlate] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [PantryToPlate] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [PantryToPlate] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [PantryToPlate] SET ARITHABORT OFF 
GO
ALTER DATABASE [PantryToPlate] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [PantryToPlate] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [PantryToPlate] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [PantryToPlate] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [PantryToPlate] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [PantryToPlate] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [PantryToPlate] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [PantryToPlate] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [PantryToPlate] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [PantryToPlate] SET  DISABLE_BROKER 
GO
ALTER DATABASE [PantryToPlate] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [PantryToPlate] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [PantryToPlate] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [PantryToPlate] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [PantryToPlate] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [PantryToPlate] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [PantryToPlate] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [PantryToPlate] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [PantryToPlate] SET  MULTI_USER 
GO
ALTER DATABASE [PantryToPlate] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [PantryToPlate] SET DB_CHAINING OFF 
GO
ALTER DATABASE [PantryToPlate] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [PantryToPlate] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [PantryToPlate] SET DELAYED_DURABILITY = DISABLED 
GO
USE [PantryToPlate]
GO
/****** Object:  Table [dbo].[Inventory]    Script Date: 8/15/2020 8:24:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Inventory](
	[Ingredient_ID] [int] IDENTITY(1,1) NOT NULL,
	[Ingredient_Name] [nvarchar](50) NOT NULL,
	[Quantity] [int] NOT NULL,
	[Quantity_Units] [nvarchar](50) NULL,
	[Storage_Location] [nvarchar](50) NULL,
	[Expiration_Date] [date] NULL,
	[Ingredient_Type] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[Ingredient_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Recipe_Ingredient]    Script Date: 8/15/2020 8:24:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Recipe_Ingredient](
	[RI_ID] [int] IDENTITY(1,1) NOT NULL,
	[Recipe_ID] [int] NULL,
	[Recipe_Name] [nvarchar](50) NOT NULL,
	[Ingredient_ID] [int] NULL,
	[Ingredient_Name] [nvarchar](50) NOT NULL,
	[Quantity_Required] [int] NOT NULL,
	[Units_Required] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[RI_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Recipes]    Script Date: 8/15/2020 8:24:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Recipes](
	[Recipe_ID] [int] IDENTITY(1,1) NOT NULL,
	[Recipe_Name] [nvarchar](50) NOT NULL,
	[Recipe_Description] [nvarchar](max) NULL,
	[Recipe_Type] [nvarchar](max) NULL,
	[Recipe_Link] [nvarchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[Recipe_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET IDENTITY_INSERT [dbo].[Inventory] ON 

INSERT [dbo].[Inventory] ([Ingredient_ID], [Ingredient_Name], [Quantity], [Quantity_Units], [Storage_Location], [Expiration_Date], [Ingredient_Type]) VALUES (3, N'Butter', 4, N'Cups', N'Refridgerator', CAST(N'2020-08-02' AS Date), N'Dairy')
INSERT [dbo].[Inventory] ([Ingredient_ID], [Ingredient_Name], [Quantity], [Quantity_Units], [Storage_Location], [Expiration_Date], [Ingredient_Type]) VALUES (4, N'Chicken Nuggets', 3, N'Pounds', N'Freezer', CAST(N'2022-10-10' AS Date), N'Frozen Meal')
INSERT [dbo].[Inventory] ([Ingredient_ID], [Ingredient_Name], [Quantity], [Quantity_Units], [Storage_Location], [Expiration_Date], [Ingredient_Type]) VALUES (5, N'Cheddar Cheese', 16, N'Ounces', N'Refridgerator', CAST(N'2020-10-10' AS Date), N'Dairy')
INSERT [dbo].[Inventory] ([Ingredient_ID], [Ingredient_Name], [Quantity], [Quantity_Units], [Storage_Location], [Expiration_Date], [Ingredient_Type]) VALUES (6, N'Cereal', 1, N'Box', N'Cabinet', CAST(N'2020-10-10' AS Date), N'Breakfast')
INSERT [dbo].[Inventory] ([Ingredient_ID], [Ingredient_Name], [Quantity], [Quantity_Units], [Storage_Location], [Expiration_Date], [Ingredient_Type]) VALUES (7, N'Ham', 16, N'Slices', N'Refridgerator', CAST(N'2020-10-10' AS Date), N'Deli')
INSERT [dbo].[Inventory] ([Ingredient_ID], [Ingredient_Name], [Quantity], [Quantity_Units], [Storage_Location], [Expiration_Date], [Ingredient_Type]) VALUES (8, N'Swiss Cheese', 4, N'Slices', N'Refridgerator', CAST(N'2020-10-10' AS Date), N'Dairy')
INSERT [dbo].[Inventory] ([Ingredient_ID], [Ingredient_Name], [Quantity], [Quantity_Units], [Storage_Location], [Expiration_Date], [Ingredient_Type]) VALUES (9, N'Mustard', 12, N'Ounces', N'Refridgerator', CAST(N'2020-12-12' AS Date), N'Condiment')
INSERT [dbo].[Inventory] ([Ingredient_ID], [Ingredient_Name], [Quantity], [Quantity_Units], [Storage_Location], [Expiration_Date], [Ingredient_Type]) VALUES (10, N'American Cheese', 20, N'Slices', N'Refridgerator', CAST(N'2020-02-02' AS Date), N'Dairy')
INSERT [dbo].[Inventory] ([Ingredient_ID], [Ingredient_Name], [Quantity], [Quantity_Units], [Storage_Location], [Expiration_Date], [Ingredient_Type]) VALUES (11, N'Eggs', 12, N'Eggs', N'Refridgerator', CAST(N'2020-09-09' AS Date), N'Dairy')
INSERT [dbo].[Inventory] ([Ingredient_ID], [Ingredient_Name], [Quantity], [Quantity_Units], [Storage_Location], [Expiration_Date], [Ingredient_Type]) VALUES (12, N'Tomatoes', 4, N'tomatoes', N'counter', CAST(N'2020-04-04' AS Date), N'Vegetable')
INSERT [dbo].[Inventory] ([Ingredient_ID], [Ingredient_Name], [Quantity], [Quantity_Units], [Storage_Location], [Expiration_Date], [Ingredient_Type]) VALUES (13, N'Spaghetti Noodle', 1, N'Box', N'Cabinet', CAST(N'2021-08-28' AS Date), N'Pasta')
INSERT [dbo].[Inventory] ([Ingredient_ID], [Ingredient_Name], [Quantity], [Quantity_Units], [Storage_Location], [Expiration_Date], [Ingredient_Type]) VALUES (14, N'Lettuce', 5, N'Ounces', N'Refridgerator', CAST(N'2020-08-14' AS Date), N'Vegetable')
INSERT [dbo].[Inventory] ([Ingredient_ID], [Ingredient_Name], [Quantity], [Quantity_Units], [Storage_Location], [Expiration_Date], [Ingredient_Type]) VALUES (24, N'Milk', 2, N'gallons', N'Refridgerator ', CAST(N'2020-08-12' AS Date), N'Dairy')
INSERT [dbo].[Inventory] ([Ingredient_ID], [Ingredient_Name], [Quantity], [Quantity_Units], [Storage_Location], [Expiration_Date], [Ingredient_Type]) VALUES (25, N'Zucchini', 16, N'oz', N'Refridgerator', CAST(N'2020-08-21' AS Date), N'Vegetables')
INSERT [dbo].[Inventory] ([Ingredient_ID], [Ingredient_Name], [Quantity], [Quantity_Units], [Storage_Location], [Expiration_Date], [Ingredient_Type]) VALUES (26, N'Salmon', 2, N'filets ', N'Refridgerator', CAST(N'2020-08-21' AS Date), N'Fish')
INSERT [dbo].[Inventory] ([Ingredient_ID], [Ingredient_Name], [Quantity], [Quantity_Units], [Storage_Location], [Expiration_Date], [Ingredient_Type]) VALUES (27, N'Russet Potatoes', 5, N'potatoes', N'cabinet', CAST(N'2020-08-21' AS Date), N'Vegetable')
INSERT [dbo].[Inventory] ([Ingredient_ID], [Ingredient_Name], [Quantity], [Quantity_Units], [Storage_Location], [Expiration_Date], [Ingredient_Type]) VALUES (28, N'Baby Carrots', 1, N'bag', N'Refridgerator', CAST(N'2020-08-31' AS Date), N'Vegetable')
INSERT [dbo].[Inventory] ([Ingredient_ID], [Ingredient_Name], [Quantity], [Quantity_Units], [Storage_Location], [Expiration_Date], [Ingredient_Type]) VALUES (29, N'Baking Soda', 6, N'oz', N'Cabinet', CAST(N'2020-08-21' AS Date), N'Baking')
SET IDENTITY_INSERT [dbo].[Inventory] OFF
SET IDENTITY_INSERT [dbo].[Recipe_Ingredient] ON 

INSERT [dbo].[Recipe_Ingredient] ([RI_ID], [Recipe_ID], [Recipe_Name], [Ingredient_ID], [Ingredient_Name], [Quantity_Required], [Units_Required]) VALUES (2, 1, N'Spaghetti and Meatballs', 24, N'Milk', 2, N'cups')
INSERT [dbo].[Recipe_Ingredient] ([RI_ID], [Recipe_ID], [Recipe_Name], [Ingredient_ID], [Ingredient_Name], [Quantity_Required], [Units_Required]) VALUES (3, 1, N'Spaghetti and Meatballs', 7, N'Ham', 1, N'slice')
INSERT [dbo].[Recipe_Ingredient] ([RI_ID], [Recipe_ID], [Recipe_Name], [Ingredient_ID], [Ingredient_Name], [Quantity_Required], [Units_Required]) VALUES (6, 6, N'Zucchini Bread', NULL, N'Flour', 1, N'cup')
INSERT [dbo].[Recipe_Ingredient] ([RI_ID], [Recipe_ID], [Recipe_Name], [Ingredient_ID], [Ingredient_Name], [Quantity_Required], [Units_Required]) VALUES (7, 6, N'Zucchini Bread', 25, N'Zucchini', 2, N'cups')
INSERT [dbo].[Recipe_Ingredient] ([RI_ID], [Recipe_ID], [Recipe_Name], [Ingredient_ID], [Ingredient_Name], [Quantity_Required], [Units_Required]) VALUES (29, 18, N'Macaroni and Cheese', NULL, N'Egg', 1, N'egg')
INSERT [dbo].[Recipe_Ingredient] ([RI_ID], [Recipe_ID], [Recipe_Name], [Ingredient_ID], [Ingredient_Name], [Quantity_Required], [Units_Required]) VALUES (30, 18, N'Macaroni and Cheese', NULL, N'Macaroni', 4, N'cups')
INSERT [dbo].[Recipe_Ingredient] ([RI_ID], [Recipe_ID], [Recipe_Name], [Ingredient_ID], [Ingredient_Name], [Quantity_Required], [Units_Required]) VALUES (31, 18, N'Macaroni and Cheese', NULL, N'Cheese', 1, N'pound')
SET IDENTITY_INSERT [dbo].[Recipe_Ingredient] OFF
SET IDENTITY_INSERT [dbo].[Recipes] ON 

INSERT [dbo].[Recipes] ([Recipe_ID], [Recipe_Name], [Recipe_Description], [Recipe_Type], [Recipe_Link]) VALUES (1, N'Spaghetti and Meatballs', N'Italian Dish with pasta and ground beef balls', N'Italian', N'https://www.delish.com/cooking/recipe-ideas/recipes/a55764/best-spaghetti-and-meatballs-recipe/')
INSERT [dbo].[Recipes] ([Recipe_ID], [Recipe_Name], [Recipe_Description], [Recipe_Type], [Recipe_Link]) VALUES (2, N'Oatmeal', N'Simple breakfast', N'Breakfast', NULL)
INSERT [dbo].[Recipes] ([Recipe_ID], [Recipe_Name], [Recipe_Description], [Recipe_Type], [Recipe_Link]) VALUES (3, N'Omelette', N'Tasty French breakfast', N'Breakfast', N'https://www.incredibleegg.org/recipe/basic-french-omelet/')
INSERT [dbo].[Recipes] ([Recipe_ID], [Recipe_Name], [Recipe_Description], [Recipe_Type], [Recipe_Link]) VALUES (4, N'Ham and Swiss', N'Easy Lunch Sandwich', N'Lunch', N'https://www.delish.com/cooking/recipe-ideas/a26966279/ham-sandwich-recipe/')
INSERT [dbo].[Recipes] ([Recipe_ID], [Recipe_Name], [Recipe_Description], [Recipe_Type], [Recipe_Link]) VALUES (5, N'CheeseBurger', N'Grilled Delight', N'American', N'https://www.foodrepublic.com/recipes/all-american-cheeseburger-recipe/')
INSERT [dbo].[Recipes] ([Recipe_ID], [Recipe_Name], [Recipe_Description], [Recipe_Type], [Recipe_Link]) VALUES (6, N'Zucchini Bread', N'Delicious and Nutritious', N'Bread', N'https://www.allrecipes.com/recipe/6698/moms-zucchini-bread/')
INSERT [dbo].[Recipes] ([Recipe_ID], [Recipe_Name], [Recipe_Description], [Recipe_Type], [Recipe_Link]) VALUES (18, N'Macaroni and Cheese', N'Easy family-friendly side', N'American', N'https://www.thepioneerwoman.com/food-cooking/recipes/a11497/macaroni-cheese/')
SET IDENTITY_INSERT [dbo].[Recipes] OFF
ALTER TABLE [dbo].[Recipe_Ingredient]  WITH CHECK ADD  CONSTRAINT [FK_Recipe_Ingredient_Inventory] FOREIGN KEY([Ingredient_ID])
REFERENCES [dbo].[Inventory] ([Ingredient_ID])
GO
ALTER TABLE [dbo].[Recipe_Ingredient] CHECK CONSTRAINT [FK_Recipe_Ingredient_Inventory]
GO
ALTER TABLE [dbo].[Recipe_Ingredient]  WITH CHECK ADD  CONSTRAINT [FK_Recipe_Ingredient_Recipes] FOREIGN KEY([Recipe_ID])
REFERENCES [dbo].[Recipes] ([Recipe_ID])
GO
ALTER TABLE [dbo].[Recipe_Ingredient] CHECK CONSTRAINT [FK_Recipe_Ingredient_Recipes]
GO
/****** Object:  StoredProcedure [dbo].[Delete_Inventory]    Script Date: 8/15/2020 8:24:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[Delete_Inventory]
@Ingredient_Name nvarchar(50)
AS
BEGIN

/*remove the ingredient_ID key from recipe_ingredient */
Update dbo.Recipe_Ingredient SET 
Ingredient_ID = NULL WHERE Ingredient_Name = @Ingredient_Name

/*Delete the ingredient from dbo.Inventory*/
DELETE FROM dbo.Inventory WHERE Ingredient_Name = @Ingredient_Name
END
GO
/****** Object:  StoredProcedure [dbo].[Delete_Recipe]    Script Date: 8/15/2020 8:24:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[Delete_Recipe]
@Recipe_Name nvarchar(50) 

AS
BEGIN

/* needs to be able to also delete all ingredients in the Recipe_Ingredient table for the recipe specified: */
delete from dbo.Recipe_Ingredient where Recipe_Name = @Recipe_Name
delete from dbo.Recipes where Recipe_Name = @Recipe_Name

END
GO
/****** Object:  StoredProcedure [dbo].[Insert_Inventory]    Script Date: 8/15/2020 8:24:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[Insert_Inventory]
@Ingredient_Name nvarchar(50),
@Quantity int, 
@Quantity_Units nvarchar(50), 
@Storage_Location nvarchar(50), 
@Expiration_Date date, 
@Ingredient_Type nvarchar(50)
AS
BEGIN
INSERT INTO dbo.Inventory (Ingredient_Name, Quantity,Quantity_Units ,Storage_Location,Expiration_Date,Ingredient_Type)
VALUES (@Ingredient_Name, @Quantity,@Quantity_Units,@Storage_Location,@Expiration_Date,@Ingredient_Type)
END
GO
/****** Object:  StoredProcedure [dbo].[Insert_Recipe_Info]    Script Date: 8/15/2020 8:24:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[Insert_Recipe_Info]

@Recipe_Name nvarchar(50), 
@Recipe_Description nvarchar(max), 
@Recipe_Type nvarchar(max), 
@Recipe_Link nvarchar(max)
AS
BEGIN

INSERT INTO dbo.Recipes (Recipe_Name, Recipe_Description, Recipe_Type,Recipe_Link)
Values (@Recipe_Name, @Recipe_Description, @Recipe_Type, @Recipe_Link)

END
GO
/****** Object:  StoredProcedure [dbo].[Insert_Recipe_Ingredient]    Script Date: 8/15/2020 8:24:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[Insert_Recipe_Ingredient]

@Recipe_Name nvarchar(50), 
@Ingredient_Name nvarchar(50),
@Quantity_Required nchar(10), 
@Units_Required nvarchar(50)

AS
BEGIN


/** Insert values based on user input **/
insert into dbo.Recipe_Ingredient 
(Recipe_Name, Ingredient_Name, Quantity_Required, Units_Required) 

VALUES
(@Recipe_Name, @Ingredient_Name, @Quantity_Required, @Units_Required)

END
GO
/****** Object:  StoredProcedure [dbo].[Update_Recipe_Ingredient]    Script Date: 8/15/2020 8:24:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[Update_Recipe_Ingredient]

/** Procedure to be called to automatically insert the Recipe_ID and Ingredient_ID information int
Recipe_Ingredient Table, based on the recipe and ingredient names. 
** Should be called after a set of Insert_Recipe_Ingredient values have been made for a recipe. 
**/
AS
BEGIN


/** Automatically import the recipe id based on the recipe's name **/
UPDATE RI
SET RI.Recipe_ID = R.Recipe_ID
FROM
Recipe_Ingredient RI
INNER JOIN Recipes R
ON RI.Recipe_Name = R.Recipe_Name

/** Automatically import the Ingredient_ID based on the ingredient's name **/
UPDATE RI
SET RI.Ingredient_ID = Ing.Ingredient_ID
FROM
Recipe_Ingredient RI
INNER JOIN Inventory Ing
ON RI.Ingredient_Name = Ing.Ingredient_Name

END
GO
USE [master]
GO
ALTER DATABASE [PantryToPlate] SET  READ_WRITE 
GO
