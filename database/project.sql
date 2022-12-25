USE [master]
GO

CREATE DATABASE [EmployeeDB]
GO

USE [EmployeeDB]
GO

CREATE TABLE [dbo].[employees](
	[empId] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[gender] [bit] NOT NULL,
	[birthDate] [date] NOT NULL,
	[deptId] [int] NOT NULL,
	[salary] [money] NOT NULL,
	[hireDate] [date] NOT NULL,
 CONSTRAINT [PK_employees] PRIMARY KEY CLUSTERED 
(
	[empId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[departments](
	[deptId] [int] IDENTITY(1,1) NOT NULL,
	[deptName] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_departments] PRIMARY KEY CLUSTERED 
(
	[deptId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[employees]  WITH CHECK ADD  CONSTRAINT [FK_employees_departments] FOREIGN KEY([deptId])
REFERENCES [dbo].[departments] ([deptId])
GO

ALTER TABLE [dbo].[employees] CHECK CONSTRAINT [FK_employees_departments]
GO

INSERT [dbo].[departments] ([deptName]) VALUES (N'C++')
INSERT [dbo].[departments] ([deptName]) VALUES (N'Python')
INSERT [dbo].[departments] ([deptName]) VALUES (N'Java')
GO

INSERT [dbo].[employees] ([name], [gender], [birthDate], [deptId], [salary], [hireDate]) VALUES (N'你老豆', 0, CAST(N'1999-02-02' AS Date), 1, 1234567.0000, CAST(N'2022-12-25' AS Date))
INSERT [dbo].[employees] ([name], [gender], [birthDate], [deptId], [salary], [hireDate]) VALUES (N'你老母', 1, CAST(N'2000-02-03' AS Date), 2, 54321.0000, CAST(N'2022-12-25' AS Date))
INSERT [dbo].[employees] ([name], [gender], [birthDate], [deptId], [salary], [hireDate]) VALUES (N'你家姐', 1, CAST(N'2016-03-03' AS Date), 1, 999999.0000, CAST(N'2022-12-25' AS Date))
INSERT [dbo].[employees] ([name], [gender], [birthDate], [deptId], [salary], [hireDate]) VALUES (N'你细佬', 0, CAST(N'2020-03-04' AS Date), 2, 888888.0000, CAST(N'2022-12-25' AS Date))
GO
