insert
    into
        usr(
            id,
            email,
            username,
            password,
            bio,
            image,
            created_at,
            updated_at
        )
    values(
        '6c29a547-dd13-4572-8a67-33d6a11f07e2',
        'barbara.reynholm@reynholm.test',
        'Barbara Reynholm',
        '$2a$10$W4ONlbwSijN8viOSFlFmyuYodwoFzAnyU6rI8rQFusRGur8Hrms6y', -- teste'Administrator',null,
        now(),
        now()
    ),
    (
        'df7ebd58-c5ca-4b08-b23f-ea310a8d7f32',
        'april.shephard@richestmag.test',
        'April Shephard',
        '$2a$10$oie0NG32aCZwqflfsF43BO8t6Tmx5pFxASTC3o2.Elq7NEso2zZ/.', -- teste'Analyst',null,
        now(),
        now()
    );